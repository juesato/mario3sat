document.onreadystatechange = function (event) {
	// this will also be called after user submits the form
    if (event.target.readyState !== "complete") {
        return;
    }

    console.log(event);

    var timeStart = Date.now(),
        UserWrapper = new UserWrappr(FullScreenMario.prototype.proliferate({
            "GameStartrConstructor": FullScreenMario
        }, FullScreenMario.prototype.settings.ui, true));
    
    console.log("It took " + (Date.now() - timeStart) + " milliseconds to start.");
    UserWrapper.displayHelpMenu();
};

function userEntersNewMap(str) {
	// called when user enters a new 3SAT string
	// generates a new map and loads it
	console.log("submitted");
	console.log(str);

    var timeStart = Date.now(),
        UserWrapper = new UserWrappr(FullScreenMario.prototype.proliferate({
            "GameStartrConstructor": FullScreenMario,
            "satStr": str
        }, FullScreenMario.prototype.settings.ui, true));
    
    console.log("It took " + (Date.now() - timeStart) + " milliseconds to start.");
    UserWrapper.displayHelpMenu();

}

