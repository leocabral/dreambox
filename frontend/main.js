

var clouds = 5;
var i=1;
var cloudCreator = setInterval(function() {
	$('#clouds').append('<div class=" cloud x'+i+'"></div>');
	i++;
	clouds++;
	if (i==6)
		i = 1;
	if (clouds == 35)
		clearInterval(cloudCreator);
}, 1000);


$('#clouds > div').unbind('click').click(function() {
	
})