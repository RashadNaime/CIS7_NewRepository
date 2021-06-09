/* 
    Created on : Sep 18, 2019, 6:28:09 PM
    Author     : Head First Javascript Example Project
 */


/* global model, view */

var controller = {

    onFire: function(row,column,event) {
       //This is the firstclick check, it generates the minesweeper table after the first click
        //prevents the user from getting a gameover click one
        if (board.firstClick===true){
            controller.startTimer();
            board.firstPick(row,column);//takes the first click
            board.generateGrid();       //generates grid after
            board.inilGrid();           //Assigns pictures based on number value
            board.open(row,column);     //
            board.floodFill();
            board.fillTable();
            board.array[row][column][1]=true;
            //board.outputGrid();
        }
        //This is what effects the grid after the first click
        else if (board.firstClick===false){
            if(event.button===0){
                
                if (board.array[row][column][2] === false){
                    if (board.array[row][column][1] === false){
                        board.array[row][column][1] = true;
                        board.gameCheck(); //gamecheck is to see if user has lost 
                        board.floodFill(); //floodfill is used to open multiple squares when a blank tile is selected
                        board.fillTable(); //fills the entire table
                        if(board.losegame === false){ //This is the flag check to see if user has lost
                            board.checkWin();
                        }
                    }
                    //This allows the user to open squares 
                    else if (board.array[row][column][1]===true){
                        board.checkFlag(row,column);
                        if (board.flagCheck === board.array[row][column][0]){
                            board.open(row,column);
                            board.floodFill();
                        }
                        
                        board.gameCheck();
                        board.fillTable();
                        board.floodFill();
                    if(board.losegame === false){
                        board.checkWin();
                        }
                    }
                    
                }

                //This uses the floodfill function if zero square is clicked
                if (board.array[row][column][0] === 0)
                    board.floodFill();
            }
            //This is the right click for flagging
            //It does not reveal the square but places a flag
            //If the tile is right clicked again it removes the flag
            else if (event.button===2){
                if(board.array[row][column][1] === false){

                    if(board.array[row][column][2]===true)
                        board.array[row][column][2] = false;

                    else if(board.array[row][column][2]===false)
                        board.array[row][column][2] = true;  
                    
                    board.fillTable();
                }
                board.flagCount();
                var tempFlags = board.numBombs - board.numFlags;
                document.getElementById("numFlags").innerHTML = "Bombs Remaining: " + tempFlags;
        }
    }  
        board.fillTable();   
        board.firstClick = false;
    },
    startTimer: function(){
        var timerVar = setInterval(countTimer,1000);
            var totalSeconds = 0;
            function countTimer() {
                if (board.wongame === true){
                    //alert("You have won");
                    clearInterval(timerVar);
                     
                }
                if (board.losegame === true){
                    //alert("You clicked on a bomb");
                    clearInterval(timerVar);
                    
                }
               ++totalSeconds;
               var hour = Math.floor(totalSeconds/3600);
               var minute = Math.floor((totalSeconds - hour*3600)/60);
               var seconds = totalSeconds - (hour*3600 + minute*60);
               var temp = (hour*3600 + minute*60);
               if (seconds<10){
                   seconds = '0' + seconds;
               }
               if (minute<10){
                   minute = '0' + minute;
               }

               document.getElementById("timer").innerHTML = seconds;
               //board.time = minute + ":" + seconds;
               board.time = '' + minute + seconds;
               //alert(board.time);
               
               
            }
            
    }

};