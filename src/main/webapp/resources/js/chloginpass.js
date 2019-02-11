
    function validate() {
        if (document.passlogform.j_username.value === "" && document.passlogform.j_password.value === "") {
            document.passlogform.j_username.focus();
            return false;
        }
        if (document.passlogform.j_username.value === "") {
            document.passlogform.j_username.focus();
            return false;
        }
        if (document.passlogform.j_password.value === "") {
            document.passlogform.j_password.focus();
            return false;
        }
    }
