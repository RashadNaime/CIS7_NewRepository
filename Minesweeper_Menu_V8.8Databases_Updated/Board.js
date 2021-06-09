var board = {
    time: 0,
    row: 0,             //Number of rows for table
    col: 0,             //Number of columns for the table
    numBombs: 0,        //Number of bombs on the grid
    flagCheck: 0,       //Checks number of flags around a certain cell
    numSquares: 0,     //Number of squares that aren't bombs
    firstClick: true,   //This makes sure that the first input will not cause a game over
    losegame: false,    //If player has clicked on a mine losegame flag is set to true
    wongame: false,
    score: 0,
    //This is the array to hold all the values of the minesweeper table
    array:[
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]],
        [["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false],["0",false,false]]],
    
    //This allows users to click on the board, the element is also called in the table
    fire: function(clicked_id,event) {
        //row and column pass the id of the table
        //Assigns row to first two characters of id
        var row = clicked_id.charAt(0) + clicked_id.charAt(1);
        //Assigns column to last two characters of the id
        var column = clicked_id.charAt(2) + clicked_id.charAt(3);
        
        //Sets the row to a single digit if less than 10
        if (row.charAt(0) === 0)
            row = row.charAt(1);
        
        //Sets column to a single digit if less than 10
        if (column.charAt(0) === 0)
            column = column.charAt(1);
        
        //Converts row to an int
        row = row*1;
        //Converts columns to an int
        column = column*1;
        controller.onFire(row,column,event);
        
    },
    
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
    generateGrid: function() {
        //Create and generate a set number of bombs for the size of the qrid
        var count = 0;
        while(count<board.numBombs)
        {
            //randomize the bombs throughout the grid, again after the first click
            var i=Math.floor(Math.random() * board.row);
            var j=Math.floor(Math.random() * board.col); 
            //This checks to see if there is already a bomb in place, if not then place the bomb within the grid
            if(this.array[i][j][0] !== -1 && this.array[i][j][0] !== 9){
                count++;
                this.array[i][j][0] = -1;
            }
        }
    },
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
    inilGrid: function(){
        for(var x = 0; x < board.row; x++){
            for(var y = 0; y < board.col; y++){
   
                if (this.array[x][y][0]!== -1){  //Only loops if point is not a bomb
                    var count = 0;          //Keeps track of number of bombs for each point
                    var startX = x-1;       //Starting point of 3x3 for x
  
                    for(var p = 0; p < 3; p++){
                        var startY = y-1;   //Starting point of 3x3 for y
                        for (var i = 0; i < 3; i++){

                            //Checks that the window is moving within the range of the grid
                            if (startX>=0 && startY>=0 && startX<board.row && startY<board.col){
                                if (this.array[startX][startY][0]===-1){
                                    count++;
                                }
                            }                
                            startY++; //Moves to next column of 3x3
                        }
                    startX++;         //Moves to next row of 3x3
                    }
                    //End of calculating bombs for this specific point
                    this.array[x][y][0] = count; //Assigns point on grid to number of bombs found
                }
            }
        }
    },
////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////    
    //Output function with document.write, No longer useful tbh
    outputGrid: function(){
        for (var x = 0 ; x < board.row; x++){
            for (var y = 0; y < board.col; y++){
                document.write(this.array[x][y][0]);
                document.write("  ");
            }
            document.write("<br>");
        }
    },
////////////////////////////////////////////////////////////////    
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////    
    fillTable: function(){
        //create the entire 7 X 7 grid
        for(var x = 0; x < board.row; x++){
            for (var y = 0; y < board.col; y++){
                if (x < 10)
                    var xpos = "0" + x.toString();
                else 
                    var xpos = x.toString();
                
                if (y < 10)
                    var ypos = "0" + y.toString();
                else 
                    var ypos = y.toString();

                x = x*1;
                y = y*1;
                var location = xpos + ypos;
                //set cells equal to the x and y position, which would be the square
                var cell = document.getElementById(location);
                
                // This sets attributes to each square, bombs being -1, 0 has floodfill
                if(this.array[x][y][1] === true)
                {
                    if (this.array[x][y][0] === 0)
                        cell.setAttribute("class", "zero");
                    else if (this.array[x][y][0] === -1)
                        cell.setAttribute("class", "bomb");
                    else if (this.array[x][y][0] === 1)
                        cell.setAttribute("class", "one");
                    else if (this.array[x][y][0] === 2)
                        cell.setAttribute("class", "two");
                    else if (this.array[x][y][0] === 3)
                        cell.setAttribute("class", "three");
                    else if (this.array[x][y][0] === 4)
                        cell.setAttribute("class", "four");
                    else if (this.array[x][y][0] === 5)
                        cell.setAttribute("class", "five");
                    else if (this.array[x][y][0] === 6)
                        cell.setAttribute("class", "six");
                    else if (this.array[x][y][0] === 7)
                        cell.setAttribute("class", "seven");
                    else if (this.array[x][y][0] === 8)
                        cell.setAttribute("class", "eight");
                }
                //This is false so it shows that square has not been clicked on
                else if (this.array[x][y][1] === false){
                    cell.setAttribute("class", "closed");
                }
                //This shows the a flag placed on a square
                if (this.array[x][y][2] === true){
                    cell.setAttribute("class", "flag");
                }
                
            }
        }
    },
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////   
    startOpen: function(x,y){
        if (x>=0 && y>=0 && x<board.row && y<board.col && board.array[x][y][2] === false)
            board.array[x][y][1] = true;
    },
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
    open: function(x,y){
        board.startOpen(x,y-1);
        board.startOpen(x,y*1+1);
        board.startOpen(x-1,y);
        board.startOpen(x-1,y-1);
        board.startOpen(x-1,y*1+1);
        board.startOpen(x*1+1,y-1);
        board.startOpen(x*1+1,y);
        board.startOpen(x*1+1,y*1+1);
    },
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
    floodFill: function(){
        //The floodfill function is used to open all 0 squares next to a 0 square that was clicked
        for(var t = 0; t < 100; t++){
            for(var x = 0; x < board.row; x++){
                
                
                for(var y = 0; y < board.col; y++){
                    if (this.array[x][y][0] === 0){
                        if (this.array[x][y][1] === true){
                            board.open(x,y); //opens all 0 squares
                        }
                    }
                }
                
                
            }
        }
    },
    //firstpick is used to make sure none of the squares on initial click are bombs 
    firstPick: function(x,y){
        board.noBomb(x,y);
        board.noBomb(x,y-1);
        board.noBomb(x,y*1+1);
        board.noBomb(x-1,y);
        board.noBomb(x-1,y-1);
        board.noBomb(x-1,y*1+1);
        board.noBomb(x*1+1,y);
        board.noBomb(x*1+1,y-1);
        board.noBomb(x*1+1,y*1+1);
    },
    //Nobomb does not allow any bombs to be placed on the initial grid
    noBomb: function(x,y){
        if (x>=0 && y>=0 && x<board.row && y<board.col){
            this.array[x][y][0] = 9;
        }
    },
    //This function is used for the lose condition
    gameCheck: function(){
        //The flag clickbomb checks to see if any bombs have been selected
        var clickBomb = false;
        for (var x = 0; x < board.row; x++){
            for (var y = 0; y < board.col; y++){
                //If the tile has a bomb, and if it is revealed that sets the flag to true
                if (this.array[x][y][0]===-1 && this.array[x][y][1]===true &&this.array[x][y][2]===false)
                    clickBomb = true;
            }
        }
        //This counts all current squares that are open 
        //This count will help determine the score of the player
        var counter = 0;
        for (var x = 0; x < board.row; x++){
            for (var y = 0; y < board.col; y++){
                if (this.array[x][y][1]===true && this.array[x][y][0]!==-1){
                        counter++;
                }
            }
        }
        //alert(counter);
        //using the count of the squares the score will be determined 
        board.score = counter * 1000; 
        //If clickbomb is set to true show losing message and reveal the rest of the board
        if (clickBomb === true){
            alert("You clicked on a bomb");
        for(x = 0; x < board.row; x++){
            for(y=0; y <board.col; y++){
                this.array[x][y][1] = true; 
            }
        }
        //alert(board.score);
        //This the variable for score that will be passed into the database
        //Storing the score value into a cookie to pass onto the php database page. 
        var $score = board.score;
        //alert($score);
        var $scorecookie = JSON.stringify($score); 
        setCookie("scoretotal",$scorecookie,1);
        //checkCookie("scoretotal");
          board.fillTable();
          this.losegame = true; //set the losegame flag equal to true 
          //If the game is lost count all the tiles on the board except for the mines to determine the score

          
        }
 
    },
    checkWin: function(){
        //Counts the entire board, the win condition is if all tiles except for mines have been clicked
        var counter = 0;
        for (var x = 0; x < board.row; x++){
            for (var y = 0; y < board.col; y++){
                if (this.array[x][y][1]===true && this.array[x][y][0]!==-1)
                    counter++;
            }
        }
        if (counter===board.numSquares){
            for(x = 0; x < board.row; x++){
                for(y=0; y <board.col; y++){
                    this.array[x][y][1] = true; 
                }
            }
            
        //This the variable for score that will be passed into the database
        //Storing the score value into a cookie to pass onto the php database page.             
            board.score = counter * 1000;
            //alert(board.score);
            var $score = board.score;
            //alert($score);
            var $scorecookie = JSON.stringify($score); 
            setCookie("scoretotal",$scorecookie,1);
            //alert($score);
            board.fillTable();
            board.wongame = true;
            alert("You have won");
        }
    },
    
    flagCount: function(){
        //Counts the entire board, the win condition is if all tiles except for mines have been clicked
        var counter = 0;
        for (var x = 0; x < board.row; x++){
            for (var y = 0; y < board.col; y++){
                if (this.array[x][y][2]===true)
                    counter++;
            }
        }
        board.numFlags = counter;
    },
    
    checkFlag: function(row,col){
        board.flagCheck = 0;
        board.flagCheckOne(row,col-1);
        board.flagCheckOne(row,col*1+1);
        board.flagCheckOne(row-1,col);
        board.flagCheckOne(row-1,col-1);
        board.flagCheckOne(row-1,col*1+1);
        board.flagCheckOne(row*1+1,col-1);
        board.flagCheckOne(row*1+1,col);
        board.flagCheckOne(row*1+1,col*1+1);
        
        //alert(board.flagCheck);
    },
    flagCheckOne: function(x,y){    
        if (x>=0 && y>=0 && x<board.row && y<board.col && board.array[x][y][2] === true)
            board.flagCheck++;
    }
    
//    fillTimer: function(id){
//        var array = [];
//
//        
//        for (var i = 0; i < 4; i++){
//            array[i] = id.charAt(i) * 1;
//            
//            var location = "t" + i;
//            var cell = document.getElementById(location);
//            
//            for (var y = 0; y < 10; y++){
//                if (array[i] === y*1){
//                    cell.setAttribute("class","timer" + y);
//                }
//            }
//            
//            cell.setAttribute("class", "zero");
//        }
//    }
};

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//Cookie Functions to pass over info to the database 
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function checkCookie(cname) {
    var value = getCookie(cname);
    if (value != "") {
        alert("Cookie Name = " + cname + "Value = " + value);
    } else {
        value = prompt("Please enter your cookie :"+cname+" Value", "");
        if (value != "" && value != null) {
            setCookie(cname, value, 365);
        }
    }
}

function clearCookie(cname){
    return "";
}
function nextPage() {
//This function links to the final receipt page, however the user needs to verify and save information
//The count will be 3 if all regular expression matches were successful
//The flag saveinfo should be true if the information was saved
window.open("MinesweeperDatabase.php");
}


