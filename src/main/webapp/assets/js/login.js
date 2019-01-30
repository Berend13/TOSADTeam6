function login() {
	$("#login_bttn").click(function(event) {
		event.preventDefault();

		var email = $("#email").val();
		var password = $("#password").val();

		$.ajax({
			url: '/api/auth/login',
			type: 'POST',
			data: {email: email, password: password},
		})
		.done(function(response) {
			if(response == null){
				swal("Oops password of email is fout");
			}else{
				swal("Login successvol");

				// opslaan in session storage
				window.sessionStorage.setItem("id", response.id);
				window.sessionStorage.setItem("name", response.name);
				window.sessionStorage.setItem("email", response.email);
				window.sessionStorage.setItem("type", response.type);

				// redirect
				window.location.href = 'home.html';

				
			}
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			console.log("complete");
		});
		
	});
}

function logOut() {
	$('.logout').click(function() {
		swal({
			title: "Uitloggen?",
			text: "Bent u zeker dat u wilt uitloggen?",
			type: "warning",
			showCancelButton: true,
			confirmButtonText: "Uitloggen",
		}, function(){
			setTimeout(function(){ 
				swal({
					title: "Uitloggen",
					text: "Totziens",
					type: "success",
					timer: 2000,
					showConfirmButton: false,
				}, function(){
					// zeker uitloggen
					sessionStorage.clear();
					window.location.href = 'http://hiphopsu.herokuapp.com/cms/';
				}); 
			}, 500);
		});
	});
}


function checkLogin(page) {
	var id = sessionStorage.getItem("id");
	var name = sessionStorage.getItem("name");
	var email = sessionStorage.getItem("email");
	var type = sessionStorage.getItem("type");	

	if (id == undefined || name == undefined || email == undefined || type == undefined) {
		if (page != 'login') {
			window.location.href = 'index.html';
			sessionStorage.clear();
		}
	}else{
		if (page == 'login') {
			window.location.href = 'home.html';
		}
	}
}