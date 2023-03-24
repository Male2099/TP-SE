<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home Page</title>
  <script src="./assets/tailwindcss.js"></script>
  <style>
    .input:hover{
      background-color: blue;
    }
  </style>
</head>

<body class="absolute top-[50%] left-[50%] translate-x-[-50%] translate-y-[-50%]">

  <div >
    <center>
      <div class="mb-5 text-2xl text-green-500">
        Welcome to Login and Singup Webapp
      </div>
    </center>
  </div>
  <div class="w-[40%] mx-auto flex justify-center gap-20 text-[1.5rem]">
    <div class="">
      <center>
        <input class="input cursor-pointer bg-black rounded-lg px-10 py-2 text-white shadow-lg" type=button
          onClick="location.href='./task1.jsp'" value='Login'>
      </center>
    </div>
    <div class="">
      <center>
        <input class="input cursor-pointer bg-black rounded-lg px-10 py-2 text-white shadow-lg" type=button
          onClick="location.href='./task2.jsp'" value='Sign Up'>
      </center>
    </div>
  </div>
</body>

</html>