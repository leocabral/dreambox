$('img.robot').unbind('click').click(function(e) {
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

$('img.robot').unbind('click').click(function(e) {
    $('.thinking').fadeIn();
    e.stopPropagation();
});

var clouds = 0;
var createCloud = function () {
	var cloud = $('<div class=" cloud"></div>').appendTo('#clouds');
	var top = Math.floor((Math.random() * 8)) * 5;
	var moveSpeed = Math.floor((Math.random() * 3) + 6) * 3;
	var scale = Math.floor((Math.random() * 3) + 6) / 10;
	cloud.css({
		'top' : top + '%',
		'-webkit-transform' : 'scale(' + scale + ')',
		'-moz-transform' : 'scale(' + scale + ')',
		'transform' : 'scale(' + scale + ')',
		'opacity' : scale,
		'-webkit-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
		'-moz-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
		'-o-animation' : 'moveclouds ' + moveSpeed + 's linear infinite'
	});
	
	clouds++;
}
var buildCreator = function (interval) {
	var creator = setInterval(function() {
		createCloud();
		if (clouds >= 50) {
			clearInterval(creator);
		}
	}, interval);
	return creator;
}
buildCreator(800);
buildCreator(1000);
buildCreator(1200);

var bindClouds = function() {
	$('#clouds').on('click', '> div', function(e) {
		var cloud = $(this);
		$.getJSON('/api/dreams/random').done(function(dream) {
			$.get('/template/opened-dream.html').done(function(template) {
				cloud.animate({
					left : ($(window).width()/2-$(cloud).width()/2)+'px',
				}, 40);
				cloud.css({
					'-webkit-animation': 'none',
					'-moz-animation': 'none',
					'-o-animation': 'none',
					'animation' : 'none'
				});
				cloud.addClass('active-cloud');
				cloud.html(doT.template(template)(dream));
				
			});
		});
		unbindClouds();
	    e.stopPropagation();
	});
};
var unbindClouds = function() {
	$('#clouds').unbind("click");
}

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
	if(!$(e.target).hasClass('active-cloud')) {
		var moveSpeed = Math.floor((Math.random() * 3) + 6) * 3;
		$('.active-cloud').removeClass('active-cloud').html('').css({
			'-webkit-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
			'-moz-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
			'-o-animation' : 'moveclouds ' + moveSpeed + 's linear infinite',
			'animation' : 'moveclouds ' + moveSpeed + 's linear infinite'
		});
	    bindClouds();
	}
    $('.thinking').fadeOut();
});

bindClouds();

$('#salvar-sonho').unbind('click').click(function() {
    $.post('/api/dreams',{
       name: $('#titulo-sonho').val(),
        description: $('#descricao-sonho').val(),
        dreamer: 5639445604728832
    });
})
Cloud.buildCreator(800);
Cloud.buildCreator(1000);
Cloud.buildCreator(1200);

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
