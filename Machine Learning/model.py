import pandas as pd
import tensorflow as tf
from flask import Flask, request, jsonify
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import LabelEncoder

app = Flask(__name__)

def load_data():
    # Read dataset
    df = pd.read_csv('data.csv')
    return df

def preprocess_data(df):
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

    return X_train_scaled, X_test_scaled, y_train, y_test, label_encoder

def build_model(X_train_scaled, y_train, label_encoder):
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

    return model

@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json()
    desain = data['desain']
    logika = data['logika']
    data_input = [[desain, logika, data['data']]]

    # Load saved model and perform prediction
    loaded_model = tf.keras.models.load_model('model.h5')
    prediction = loaded_model.predict(data_input)

    # Decode prediction to class labels
    class_labels = label_encoder.classes_
    predicted_class_index = prediction.argmax(axis=1)
    predicted_class = class_labels[predicted_class_index[0]]

    return jsonify({'prediction': predicted_class})

if __name__ == '__main__':
    df = load_data()
    X_train, X_test, y_train, y_test, label_encoder = preprocess_data(df)
    trained_model = build_model(X_train, y_train, label_encoder)
    trained_model.save("model.h5")
    app.run(debug=True)
