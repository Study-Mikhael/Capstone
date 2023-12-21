<B>

# API => U S E R

## - Sign Up 
```
Endpoint : POST /api/users/signup
```
Request Body :


```json
{
    "email": "rusli@gmail.com",
    "name":"rusli",
    "password":"sstt"
}
```
Response Body Success :

```json
{
    "error": false,
    "message": "User Created"
}
```
Response Body Failed :

```json
{
    "errors": "email already exists"
}
```
## - Login User API
```
Endpoint : POST /api/users/login
```
Request Body :

```json
{
    "email": "rusli@gmail.com",
    "password":"sstt"
}
```
Response Body Success :

```json
{
    "error": false,
    "message": "Success Bro",
    "loginResult": {
        "email": "test@gmail.com",
        "name": "test",
        "token": "0cf22e11-70dc-49c8-9d26-fb2522ec809a"
    }
}
```

Response Body Failed :

```json
{
    "errors": "email or password wrong"
}
```

## - Get User API
```
Endpoint : GET /api/users/current
```
Headers:
- Authorization : token

Response Body Success :
```json
{
    "data": {
        "email": "rusli@gmail.com",
        "name": "rusli"
    }
}
```

Response Body errors :
```json
{
    "error":"unauthorized",
}
```

## - Update User API
```
Endpoint : PATCH /api/users/
```


Headers:
- Authorization : token

Request Body :

```json
{
    "name":"Bujang",    //optional
    "password":"hidup" //optional
}
```

Response Body Success :

```json
{
    "data": {
        "email": "rusli@gmail.com",
        "name": "Bujang"
    }
}
```
Response Body Failed :

```json
{
    "errors": "Name Max length 100"
}

```


## - Logout User API
```
Endpoint : DELETE api/users/logout
```
Headers:
- Authorization : token

Response Body Success :

```json
{
    "error": false,
    "message": "Logout Success"
}
```
Response Body errors :
```json
{
    "error":"unauthorized",
}
```
## =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  = = =  =  =  =  =  =  
# API => C O U R S E

## Create Course API

```
Endpoint : POST /api/course/create
```

Headers : 
- Authorization : token

Request Body :

```json
{
  "courseName" : "Belajar Ngaji",
  "thumbnail" : "src/assets/html.png",
  "courseType" : "Backend",
  "describe" : "ini adalah sebuah deskripsi",
  "learning" : "ini adalah sebuah materi dengan total 1000 kata"
}
```

Response Body Success : 

```json
{
    "data": {
        "id": 7,
        "courseName": "Belajar Ngaji",
        "thumbnail": "src/assets/html.png",
        "courseType": "Backend",
        "describe": "ini adalah sebuah deskripsi",
        "learning": "ini adalah sebuah materi dengan total 1000 kata"
    }
}
```

Response Body Error :

```json
{
  "errors" : "Course is not valid format"
}
```

## Get Course API
```
Endpoint : GET /api/course
```
Headers :
- Authorization : token

Response Body Success :

```json
{
    "error": false,
    "message": "All course retrieved",
    "data": [
        {
            "id": 4,
            "courseName": "Belajar HTML",
            "thumbnail": "src/assets/html.png",
            "courseType": "Frontend"
        },
        {
            "id": 5,
            "courseName": "Belajar HTML",
            "thumbnail": "src/assets/html.png",
            "courseType": "Backend"
        }
    ]
}
```

Response Body Error :

```json
{
  "errors" : "Course is not found"
}
```

## Get Detail Course API
```
Endpoint : GET /api/course/:id |(5)
```
Headers :
- Authorization : token

Response Body Success :

```json
{
    "error": false,
    "message": "Contact detail retrieved",
    "data": {
        "id": 5,
        "thumbnail": "src/assets/html.png",
        "describe": "ini adalah sebuah deskripsi",
        "learning": "ini adalah sebuah materi dengan total 1000 kata"
    }
}
```

Response Body Error :

```json
{
  "errors" : "Course is not found"
}
```

# API => Question
## Create Question 

```
Endpoint : POST /api/question/create
```
Headers :
- Authorization : token

Request Body:

```json
{
    "queText": "Kalau Kamu Mendapatkan Kesempatan Belajar, Kamu Lebih Tertarik Untuk Belajar Apa?",
    "answer1":"FrontEnd",
    "answer2":"BackEnd",
    "answer3":"Data Science"

}
```

Response Body Success :

```json
{
    "data": {
        "id": 17,
        "queText": "Kalau Kamu Mendapatkan Kesempatan Belajar, Kamu Lebih Tertarik Untuk Belajar Apa?",
        "answer1": "FrontEnd",
        "answer2": "BackEnd",
        "answer3": "Data Science"
    }
}
```
## Get Question

```
Endpoint : GET /api/question
```
Headers :
- Authorization : token

Response Body Success :
```json
{
    "error": false,
    "message": "All Question retrieved",
    "data": [
        {
            "id": 10,
            "queText": "Apa yang Lebih Seru Buat Kamu?",
            "answer1": "Membuat Animasi",
            "answer2": "Bermain Dengan Logika",
            "answer3": "Menganalisis Data"
        },
        {
            "id": 11,
            "queText": "Apa yang Ingin Kamu Pelajari?",
            "answer1": "Cara Membuat Tampilan Website yang Menarik",
            "answer2": "Cara Membuat Database",
            "answer3": "Cara Mengelola Data dan Menganalisis Data"
        },
        {
            "id": 12,
            "queText": "Mata Pelajaran Apa Yang Kamu Suka?",
            "answer1": "Kesenian dan Kreativitas",
            "answer2": "Matematika",
            "answer3": "Statistika"
        },
        {
            "id": 13,
            "queText": "Dari Pilihan Di Bawah Ini, Kamu Lebih Suka Melakukan yang Mana?",
            "answer1": "Mendesain ",
            "answer2": "Belajar Mengasah Logika",
            "answer3": "Mengolah Data"
        },
        {
            "id": 14,
            "queText": "Dari Pilihan Di Bawah Ini, Mana yang Paling Menarik Menurutmu?",
            "answer1": "Membuat Sketsa",
            "answer2": "Membuat Database",
            "answer3": "Mengolah dan Menganalisis Data"
        },
        {
            "id": 15,
            "queText": "Ketika Membuat Aplikasi, Hal Mana yang Kamu Prioritaskan?",
            "answer1": "Tampilan Aplikasi Yang Menarik",
            "answer2": "Menyiapkan Penyimpanan",
            "answer3": "Menyiapkan Data Yang Akurat"
        },
        {
            "id": 16,
            "queText": "Ketika Ada Penawaran Kerja, Kamu Lebih Memilih Yang Mana?",
            "answer1": "Kerja Untuk Membuat Desain",
            "answer2": "Kerja Untuk Memastikan Keamanan Aplikasi",
            "answer3": "Kerja Untuk Menganalisis Data"
        },
        {
            "id": 17,
            "queText": "Kalau Kamu Mendapatkan Kesempatan Belajar, Kamu Lebih Tertarik Untuk Belajar Apa?",
            "answer1": "FrontEnd",
            "answer2": "BackEnd",
            "answer3": "Data Science"
        }
    ]
}
```





