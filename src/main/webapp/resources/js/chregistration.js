function validatePageData() {
    if (!validateEmail()) {
        document.getElementById('email').focus();
        return false;
    }
    if (!validatePassword()) {
        document.getElementById('password').focus();
        return false;
    }
    if (!checkIfPasswordsAreEqual()) {
        document.getElementById('password').focus();
        return false;
    }
}

function checkEmailPattern(email) {
    const $re = new RegExp('^[\\w][\\w\.\\\\-]{2,}[\\w]@[a-zA-Z]{2,}\\.[a-zA-Z]{2,}$');
    return $re.test(email);
}

function checkPasswordPattern(password) {
    const $re = new RegExp('((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[\\w.\-\]{6,15})');
    return $re.test(password)
}

function validateEmail() {
    if (!checkEmailPattern(document.getElementById('email').value)) {
        if (document.getElementById('email').classList.contains('valid')) {
            document.getElementById('email').classList.remove('valid');
        }
        document.getElementById('email').classList.add('invalid');
        document.getElementById('forpass').innerText = 'email should match the pattern \n for example: example@email.com';
        return false;
    }
    else {
        if (document.getElementById('email').classList.contains('invalid')) {
            document.getElementById('email').classList.remove('invalid');
        }
        document.getElementById('email').classList.add('valid');
        document.getElementById('forpass').innerText = '';
        return true;
    }
}

function validatePassword() {
    if (!checkPasswordPattern(document.getElementById('password').value)) {
        if (document.getElementById('password').classList.contains('valid')) {
            document.getElementById('password').classList.remove('valid');
        }
        document.getElementById('password').classList.add('invalid');
        if (document.getElementById('forpass').innerText === '') {
            document.getElementById('forpass').innerText = 'password must be at least 6 characters \n with one up-case letter, one low-case and one digit';
        }
        return false;
    }
    else {
        if (document.getElementById('password').classList.contains('invalid')) {
            document.getElementById('password').classList.remove('invalid');
        }
        document.getElementById('password').classList.add('valid');
        document.getElementById('forpass').innerText = '';
        return true;
    }
}

function checkIfPasswordsAreEqual() {
    if (validatePassword()) {
        if (document.getElementById('password').value !== document.getElementById('password2').value) {
            if (document.getElementById('password2').classList.contains('valid')) {
                document.getElementById('password2').classList.remove('valid');
            }
            document.getElementById('password2').classList.add('invalid');
            if (document.getElementById('forpass').innerText === '') {
                document.getElementById('forpass').innerText = 'passwords must be equal';
            }
            return false;
        }
        else {
            if (document.getElementById('password').classList.contains('invalid')) {
                document.getElementById('password').classList.remove('invalid');
            }
            if (document.getElementById('password2').classList.contains('invalid')) {
                document.getElementById('password2').classList.remove('invalid');
            }
            document.getElementById('password').classList.add('valid');
            document.getElementById('password2').classList.add('valid');
            document.getElementById('forpass').innerText = '';
            return true;
        }
    }
    else {
        if (document.getElementById('password2').classList.contains('valid')) {
            document.getElementById('password2').classList.remove('valid');
        }
        if (document.getElementById('password2').classList.contains('invalid')) {
            document.getElementById('password2').classList.remove('invalid');
        }
        return false;
    }
}