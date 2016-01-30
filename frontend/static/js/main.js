$('img.robot').unbind('click').click(function(e) {
    $('.thinking').fadeIn();
    e.stopPropagation();
});

Cloud.buildCreator(800);
Cloud.buildCreator(1000);
Cloud.buildCreator(1200);

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