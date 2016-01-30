$('img.robot').unbind('click').click(function(e) {
    $('.thinking').fadeIn();
    e.stopPropagation();
});

$('#meu-perfil').unbind('click').click(function(e) {
    e.stopPropagation();
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
