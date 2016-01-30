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
};
createCloud();
buildCreator(800);
buildCreator(1000);
buildCreator(1200);

var openCloud = function(cloud, html) {
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
	cloud.html(html);

	$('.follow').unbind('click').click(function() {
		var button = $(this);
		var id = button.data('id');
//		$.ajax({
//			method: 'PUT',
//			url: 'some.php',
//			data: { 'id' : id }
//		}).done(function() {
		    button.prop('disabled', true);
//        });
	});
	unbindClouds();
};

var applyCloudTemplate = function(cloud, dream) {
	$.get('templates/opened-dream.html').done(function(template) {
		openCloud(cloud, doT.template(template)( {
			'dream' : dream,
			'origin' : window.location.origin
		}));
	});
};

var bindClouds = function() {
	$('#clouds').on('click', '> div', function(e) {
		var cloud = $(this);
		$.getJSON('/api/dreams/random').success(function(dream) {
			applyCloudTemplate(cloud, dream);
		});
	    e.stopPropagation();
	});
};

var unbindClouds = function() {
	$('#clouds').unbind("click");
};

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
	    bindClouds();
	}
    $('.thinking').fadeOut();
});

bindClouds();
var params = window.location.search;
if (params) {
	var entry = params.split('=');
	if (entry[0] == '?id') {
		var id = entry[1].replace('/', "");
		var cloud = $(".cloud:eq(1)");
		$.getJSON('/api/dreams/' + id).done(function(dream) {
			applyCloudTemplate(cloud, dream);
		});
	}
}
