# Video challenge api
This is a api used to encode videos from non compatible web types (e.g.: mkv) to compatible type.

To send a video to encode do a POST to "/" sending a object on the following format: 

Header: 
Content-Type: application/x-www-form-urlencoded

Body:
```json
{
    "file": <your file in here>
} 
```

For now it will return nothing. But it will return a link where you can access the file at the correct format. 