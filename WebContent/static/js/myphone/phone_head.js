var locat = (window.location+'').split('/'); 
$(function(){
	locat =  locat[0]+'//'+locat[2]+'/'+locat[3];
});
function siMenu(MENU_URL){
	var k =locat+'/'+MENU_URL;
	window.location.href=locat+'/'+MENU_URL;
}