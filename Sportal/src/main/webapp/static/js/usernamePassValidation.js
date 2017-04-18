/**
 * 
 */
function validateEmail(email) {
	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}
function checkForm(form) {
	if (form.username.value == "") {
		alert("Error: Username cannot be blank!");
		form.username.focus();
		return false;
	}
	re = /^\w+$/;
	if (!re.test(form.username.value)) {
		alert("Error: Username must contain only letters, numbers and underscores!");
		form.username.focus();
		return false;
	}

	if (form.password.value != ""
			&& form.password.value == form.confirmPassword.value) {
		if (form.password.value.length < 6) {
			alert("Error: Password must contain at least six characters!");
			form.password.focus();
			return false;
		}
		if (form.password.value == form.username.value) {
			alert("Error: Password must be different from Username!");
			form.password.focus();
			return false;
		}
		re = /[0-9]/;
		if (!re.test(form.password.value)) {
			alert("Error: password must contain at least one number (0-9)!");
			form.password.focus();
			return false;
		}
		re = /[a-z]/;
		if (!re.test(form.password.value)) {
			alert("Error: password must contain at least one lowercase letter (a-z)!");
			form.password.focus();
			return false;
		}
		re = /[A-Z]/;
		if (!re.test(form.password.value)) {
			alert("Error: password must contain at least one uppercase letter (A-Z)!");
			form.password.focus();
			return false;
		}
	} else {
		alert("Error: Please check that you've entered and confirmed your password!");
		form.password.focus();
		return false;
	}

	return true;
}