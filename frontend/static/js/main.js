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

$('#novo-sonho').unbind('click').click(function(e) {
    $('#new-cloud').addClass('active-cloud');
    e.stopPropagation();
});

$('#new-cloud').unbind('click').click(function(e) {
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

$(document).ready(function(){
	$.ajax({type:'POST',url:'api/dreams', data : {'name':'Quero ter um sonho', dreamer: 5649391675244544    , 'description':'Que seja listado no app :)', 'tags': ['sonhos']}})
});

$(document).on('click',function(e) {
	if($(e.target).parents('.active-cloud').length <= 0) {
		var moveSpeed = Math.floor((Math.random() * 3) + 6) * 3;
		$('.active-cloud').removeClass('active-cloud').html('').css({
			'-webkit-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
			'-moz-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
			'-o-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
			'animation' : 'moveclouds ' + moveSpeed + 's linear infinite'
		});
	    Cloud.bind();
	}
    $('.thinking').fadeOut();
});

var params = window.location.search;
if (params) {
	var entry = params.split('=');
	if (entry[0] == '?id') {
		var id = entry[1].replace('/', "");
		var cloud = Cloud.create();
		$.getJSON('/api/dreams/' + id).done(function(dream) {
			Cloud.open(cloud, dream);
		});
	}
}