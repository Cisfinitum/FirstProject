document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.modal');
    var instances = M.Modal.init(elems);
});

window.onload = function () {
    checkPwdChanged();
};

function checkPwdChanged () {
    var url = window.location.href;
    // Get DIV
    var msg = document.getElementById('idofthedivtohide');
    // Check if URL contains the keyword
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

function validatePageData() {
    if (!validatePassword()) {
        document.getElementById('password').focus();
        return false;
    }
    if (!checkIfPasswordsAreEqual()) {
        document.getElementById('password').focus();
        return false;
    }
}

function checkPasswordPattern(password) {
    const $re = new RegExp('((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[\\w.\-\]{6,15})');
    return $re.test(password);
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
        } else {
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
    } else {
        if (document.getElementById('password2').classList.contains('valid')) {
            document.getElementById('password2').classList.remove('valid');
        }
        if (document.getElementById('password2').classList.contains('invalid')) {
            document.getElementById('password2').classList.remove('invalid');
        }
        return false;
    }
}