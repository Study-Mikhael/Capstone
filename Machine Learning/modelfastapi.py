import pandas as pd
import tensorflow as tf
from fastapi import FastAPI, HTTPException, Request, Depends
from fastapi.responses import JSONResponse
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, LabelEncoder

app = FastAPI()

# Load dataset
df = pd.read_csv('data.csv')

# Split dataset into features (X) and labels (y)
X = df[['desain', 'logika', 'data']]
y = df['hasil']

# Convert labels to numeric form
label_encoder = LabelEncoder()
y = label_encoder.fit_transform(y)

# Split data into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Normalize features using StandardScaler
scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.transform(X_test)

# Build a simple neural network model
model = tf.keras.Sequential([
    tf.keras.layers.Dense(64, activation='relu', input_shape=(3,)),
    tf.keras.layers.Dense(32, activation='relu'),
    tf.keras.layers.Dense(len(label_encoder.classes_), activation='softmax')
])

# Compile the model
model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# Train the model
model.fit(X_train_scaled, y_train, epochs=20, batch_size=16)

# Save the model in h5 format
model.save("model.h5")

# Convert to TensorFlow Lite
converter = tf.lite.TFLiteConverter.from_keras_model(model)
tflite_model = converter.convert()

# Save TensorFlow Lite model to file
with open('converted_model.tflite', 'wb') as f:
    f.write(tflite_model)

def get_model():
    return model

def get_label_encoder():
    return label_encoder

@app.post("/predict")
async def predict(request: Request, model: tf.keras.Model = Depends(get_model), 
                  label_encoder: LabelEncoder = Depends(get_label_encoder)):
    data = await request.json()
    desain = data['desain']
    logika = data['logika']
    data_input = [[desain, logika, data['data']]]

    # Perform prediction
    prediction = model.predict(data_input)

    # Decode prediction to class labels
    class_labels = label_encoder.classes_
    predicted_class_index = prediction.argmax(axis=1)
    predicted_class = class_labels[predicted_class_index[0]]
    return JSONResponse(content={'prediction': predicted_class})
