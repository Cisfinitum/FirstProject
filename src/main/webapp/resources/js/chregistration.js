function validate() {
    if (document.user.password.value === "" && document.user.email.value === "" && document.user.password2.value === "") {
        document.user.email.focus();
        return false;
    }
    if (document.user.username.value === "") {
        document.user.username.focus();
        return false;
    }
    if (document.user.password.value === "") {
        document.user.password.focus();
        return false;
    }
    if (document.user.password2.value === "") {
        document.user.password2.focus();
        return false;
    }
    if (document.user.email.value === "") {
        document.user.email.focus();
        return false;
    }
    if (document.getElementById('password').value !== document.getElementById('password2').value){
        document.getElementById('forpass').innerText = document.getElementById("wrongpass").value;
        document.user.password2.focus();
        return false;
    }
}

function checkPass() {
   if(document.getElementById('password').value !== document.getElementById('password2').value)
    {
        document.getElementById('password').classList.add('invalid');
        document.getElementById('password2').classList.add('invalid');
    }
   else {
       document.getElementById('password').classList.add('valid');
       document.getElementById('password2').classList.add('valid');
   }
}