function validatePageData() {
    if (!validateEmail()) {
        document.getElementById('email').focus();
        return false;
    }
    if (!validateFirstName()) {
        document.getElementById('firstName').focus();
        return false;
    }
    if (!validateLastName()) {
        document.getElementById('lastName').focus();
        return false;
    }
    if (!validatePhoneNumber()) {
        document.getElementById('phoneNumber').focus();
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

function checkNamePattern(name) {
    const $re = new RegExp('^[\\w\\s]*$');
    return $re.test(name);
}

function checkEmailPattern(email) {
    const $re = new RegExp('^[\\w][\\w\.\\\\-]{2,}[\\w]@[a-zA-Z]{2,}\\.[a-zA-Z]{2,}$');
    return $re.test(email);
}

function checkPhoneNumberPattern(phoneNumber) {
    const $re = new RegExp('^[+]?[\\d\\s]{5,}$');
    return $re.test(phoneNumber)
}

function checkPasswordPattern(password) {
    const $re = new RegExp('((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[\\w.\-\]{6,15})');
    return $re.test(password)
}

function validateFirstName() {
    if (!checkNamePattern(document.getElementById('firstName').value)) {
        if (document.getElementById('firstName').classList.contains('valid')) {
            document.getElementById('firstName').classList.remove('valid');
        }
        document.getElementById('firstName').classList.add('invalid');
        document.getElementById('forpass').innerText = 'first name';
        return false;
    }
    else {
        if (document.getElementById('firstName').classList.contains('invalid')) {
            document.getElementById('firstName').classList.remove('invalid');
        }
        document.getElementById('firstName').classList.add('valid');
        document.getElementById('forpass').innerText = '';
        return true;
    }
}

function validateLastName() {
    if (!checkNamePattern(document.getElementById('lastName').value)) {
        if (document.getElementById('lastName').classList.contains('valid')) {
            document.getElementById('lastName').classList.remove('valid');
        }
        document.getElementById('lastName').classList.add('invalid');
        document.getElementById('forpass').innerText = 'last name';
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


function validatePhoneNumber() {
    if (!checkPhoneNumberPattern(document.getElementById('phoneNumber').value)) {
        if (document.getElementById('phoneNumber').classList.contains('valid')) {
            document.getElementById('phoneNumber').classList.remove('valid');
        }
        document.getElementById('phoneNumber').classList.add('invalid');
        document.getElementById('forpass').innerText = 'Phone number should match the pattern \n for example: +7911 221 17 69';
        return false;
    }
    else {
        if (document.getElementById('phoneNumber').classList.contains('invalid')) {
            document.getElementById('phoneNumber').classList.remove('invalid');
        }
        document.getElementById('phoneNumber').classList.add('valid');
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
        document.getElementById('forpass').innerText = 'password must be at least 6 characters \n with one up-case letter, one low-case and one digit';
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
            document.getElementById('forpass').innerText = 'passwords must be equal';
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