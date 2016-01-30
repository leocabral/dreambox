$('img.robot').unbind('click').click(function(e) {
	if(Helper.isLogged()) {
		$('#sign-in').hide();
		$('#sign-up').hide();
		$('#meu-perfil').show();
		$('#novo-sonho').show();
	} else {
		$('#sign-in').show();
		$('#sign-up').show();
		$('#meu-perfil').hide();
		$('#novo-sonho').hide();
	}
    $('.thinking').fadeIn();
    e.stopPropagation();
});

$('#meu-perfil').unbind('click').click(function(e) {
    e.stopPropagation();
});

$('#sign-up').unbind('click').click(function(e){
	e.stopPropagation();
	$('.thinking').fadeOut();
	Cloud.createSignupPopup();
});

$('#sign-in').unbind('click').click(function(e){
	e.stopPropagation();
	$('.thinking').fadeOut();
	Cloud.createSigninPopup();
});

Cloud.buildCreator(800);
Cloud.buildCreator(1000);
Cloud.buildCreator(1200);

$(document).on('click',function(e) {
    $('.thinking').fadeOut();
});

var params = window.location.search;
if (params) {
	var entry = params.split('=');
	if (entry[0] == '?id') {
		var id = entry[1].replace('/', "");
		var cloud = Cloud.create();
		Dream.service.find(id, function(dream) {
			Cloud.open(cloud, dream);
		});
	}
}
