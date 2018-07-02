
window.onload = function(){
	var menus = document.getElementsByClassName("menu");
	var subs = document.getElementsByClassName("submenu");
	for(var i=0;i<menus.length;i++){
		menus[i].id = i;
		menus[i].onclick = function(){
			for(var j=0;j<menus.length;j++){
				subs[j].style.display = "none";
				this.style.fontWeight = "";
			}
			this.style.color ="#1E9CE9";
			this.style.fontWeight = "bolder";
			subs[this.id].style.display = "block";
		}
	}
}