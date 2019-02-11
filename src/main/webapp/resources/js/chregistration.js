function validate() {
    if (document.user.username.value === "" && document.user.password.value === "" && document.user.email.value === "") {
        document.user.username.focus();
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
    if (document.user.email.value === "") {
        document.user.email.focus();
        return false;
    }
}