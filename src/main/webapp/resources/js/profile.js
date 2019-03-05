document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.modal');
    var instances = M.Modal.init(elems);
});

window.onload = function () {
    checkPwdChanged();
};

function checkPwdChanged () {
    var url = window.location.href;
    var msg = document.getElementById('idofthedivtohide');
    if( url.search( 'changed' ) > 0 ) {
        // Display the message
        msg.classList.add("show");
        msg.classList.remove("hide");
    }
    else {
        msg.classList.add("hide");
        msg.classList.remove("show");
    }
}

function checkNamePattern(name) {
    const $re = new RegExp('^((?=.*[a-zA-Z])[a-zA-Z\\s\'-]+)$');
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

function validateEmail(emails) {
    alert('f');
    if (!checkEmailPattern(document.getElementById('change-email-field').value)) {
        if (document.getElementById('change-email-field').classList.contains('valid')) {
            document.getElementById('change-email-field').classList.remove('valid');
        }
        document.getElementById('change-email-field').classList.add('invalid');
        return false;
    }
    else {
        alert(emails.toString());
        for (let email in emails) {
            alert(email.toString());
            if (email.localeCompare(document.getElementById('change-email-field').value) === 0) {
                if (document.getElementById('change-email-field').classList.contains('valid')) {
                    document.getElementById('change-email-field').classList.remove('valid');
                }
                document.getElementById('change-email-field').classList.add('invalid');
                return false;
            }
        }
        if (document.getElementById('change-email-field').classList.contains('invalid')) {
            document.getElementById('change-email-field').classList.remove('invalid');
        }
        document.getElementById('change-email-field').classList.add('valid');
        return true;
    }
}



function checkPreviousPassword(previousPassword) {
    if (!(document.getElementById('previous-password').value.localeCompare(previousPassword) == 0)) {
        if (document.getElementById('previous-password').classList.contains('valid')) {
            document.getElementById('previous-password').classList.remove('valid');
        }
        document.getElementById('previous-password').classList.add('invalid');
        return false;
    }
    else {
        if (document.getElementById('previous-password').classList.contains('invalid')) {
            document.getElementById('previous-password').classList.remove('invalid');
        }
        document.getElementById('previous-password').classList.add('valid');
        return true;
    }
}

function validatePassword() {
    if (!checkPasswordPattern(document.getElementById('password').value)) {
        if (document.getElementById('password').classList.contains('valid')) {
            document.getElementById('password').classList.remove('valid');
        }
        document.getElementById('password').classList.add('invalid');
        return false;
    }
    else {
        if (document.getElementById('password').classList.contains('invalid')) {
            document.getElementById('password').classList.remove('invalid');
        }
        document.getElementById('password').classList.add('valid');
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