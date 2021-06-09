// init - called when the page has completed loading

//window.onload = init;

function init(ur,uc,ub) {
    //board.generateGrid();   //Generates bomb positions
    //board.inilGrid();       //Initializes spots regarding how many bombs touching
    //board.fillTable();      //Assigns pictures, also used to output after a click
    
    document.getElementById("timer").innerHTML = "00:00";
    var rows = ur;
    var cols = uc;
    var bombs = ub;
    board.numBombs = bombs;
    board.row = rows;
    board.col = cols;
    board.numSquares = (rows*cols) - bombs;
    var table = document.getElementById('tableID');
    for (var r = 0; r < rows; r++){
        var row = table.insertRow(-1);
        for (var c = 0; c < cols; c++){
            var cell = row.insertCell(-1);
            
            var num1;
            var num2;
            
            num1 = '' + r;
            num2 = '' + c;
            if (r<10)
                num1= '0' + num1;
            if (c<10)
                num2 = '0' + num2;
                
            var givenId = '' + num1 + num2;
            cell.id = givenId;
            cell.setAttribute('onmousedown', 'board.fire(this.id,event)');
            cell.setAttribute("class", "closed");
            //alert(givenId);
        }
    }
    
    document.getElementById("numFlags").innerHTML = "Bombs Remaining: " + board.numBombs;
    var test = document.getElementById("55");
    test.setAttribute("class", "five");
    //This outputs the board using text rather than pictures
    //board.outputGrid();
    board.generateGrid();        //generates grid after
    board.inilGrid();
    board.fillTable();
}
