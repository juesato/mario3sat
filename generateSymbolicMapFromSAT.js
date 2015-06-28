function Clause(varsIn, varNegsIn) {
  /* varsIn is a list of ints
   * varNegsIn is a list of booleans
   */
   this.vars = [];
   this.varNegs = [true, true, true];

   if (arguments.length == 2) {
	   this.vars = varsIn;
	   this.varNegs = varNegsIn;   	
   }
}

function AbstractMapGrid(clauseList, numVars) { // Constructor function
	  var VARIABLE = [21,22];
	  var CLAUSE = [[31,32,33,34,35,36],[37,38,39,40,41,42]];
	  var PATH_U = 50;
	  var PATH_D = 51;
	  var PATH_L = 52;
	  var PATH_R = 53;
	  var PATH_DROP = 54;
	  
	  var ELBOW_UL = 60;
	  var ELBOW_UR = 61;
	  var ELBOW_DL = 62;
	  var ELBOW_DR = 63;
	  var ELBOW_T = 64; // T-shaped
	  var ELBOW_T_INV = 65; // Upside-down T
	  
	  var CROSSOVER_UR = 10;
	  var CROSSOVER_UL = 11;
	  var CROSSOVER_DR = 12;
	  var CROSSOVER_DL = 13;
	  
	  var EMPTY = 123;
	  
	  var START = 70;
	  var FINISH = 80;
	  
	  var BIG_NUMBER = 10;

	  this.clauseList = clauseList;
	  this.numVars = numVars

	  this.smartAdd = function (cur, added) {
	  	/* returns an int
	  	 * cur - int
	  	 * added - int
	  	 */
	      if (cur == 0) {
	          return added;
	      }
	      if ((cur == PATH_U && added == PATH_L) || (cur == PATH_L && added == PATH_U)) {
	          return CROSSOVER_UL;
	      }
	      if ((cur == PATH_U && added == PATH_R) || (cur == PATH_R && added == PATH_U)) {
	          return CROSSOVER_UR;
	      }
	      if ((cur == PATH_D && added == PATH_L) || (cur == PATH_L && added == PATH_D)) {
	          return CROSSOVER_DL;
	      }
	      if ((cur == PATH_DROP && added == PATH_L) || (cur == PATH_L && added == PATH_DROP)) {
	      	console.log("WARNING: Drop changed to crossover");
	      	return CROSSOVER_DL;
	      }
	      if ((cur == PATH_D && added == PATH_R) || (cur == PATH_R && added == PATH_D)) {
	          return CROSSOVER_DR;
	      }
	      if ((cur == PATH_DROP && added == PATH_R) || (cur == PATH_R && added == PATH_DROP)) {
	      	console.log("WARNING: Drop changed to crossover");
	      	return CROSSOVER_DR;
	      }
	      if (added == ELBOW_T_INV || cur == ELBOW_T_INV) {
	      	return ELBOW_T_INV;
	      }
	      if ((added == ELBOW_T && cur == PATH_L) || (added == ELBOW_T && cur == PATH_R) ||
	      	  (added == PATH_L && cur == ELBOW_T) || (added == PATH_R && cur == ELBOW_T)) {
	      	return ELBOW_T;
	      }
	      else {
	          console.log("SOMETHING HAS GONE VERY WRONG!!!!");
	          console.log("CUR " + cur);
	          console.log("ADDED " + added);
	          return -1;
	      }
	  }
	  
	this.getSymbolicGrid = function () {    
		/*
		 * Returns - 2D array of integers - the map macros
		 * clauses - a list of Clauses
		 * numVars - an var representing the number of variables
		 */
		var c = this.clauseList.length;
		var v = this.numVars;
		var rows = 6 * v + 4 + 3;
		var cols = 6 * c + 4 * v + 5;

		/* We will later combine paths into crossover gadgets. Yes, x, y is out of order */
		// int[][] tempGrid = new int[rows][cols] ;
		var tempGrid = [];
		for (var i = 0; i < rows; i++) {
			tempGrid.push([]);
			for (var j = 0; j < cols; j++) {
				tempGrid[i].push(0);
			}
		} // initialize to 0

		/* Set all variable gadgets */
		var var_row = 2*v + 1;
		var var_col = 2*v + 1;

		var clause_row = var_row + 4*v + 3;
		var clause_col = var_col + 2;


		// We need to keep track of which variables are in which columns
		// int[] colToClauseVar = new int[cols];

		var colToClauseVar = [];
		for (var i = 0; i < cols; i++) {
			colToClauseVar.push(-999);
		}
		var clauseY = clause_row;
		var clauseX = clause_col;

		// for (Clause clause : clauseList) {
		for (var z = 0; z < this.clauseList.length; z++) {
			var clause = this.clauseList[z];

			for (var i = 0; i < 3; i++) {
				colToClauseVar[clauseX] = clause.vars[i] +  1; // use 1-indexing since 0 = -0
				if (clause.varNegs[i] == false) {
		  		colToClauseVar[clauseX] *= -1;
				}
				clauseX += 2;
			}
		}
		// for (var i = 0; i < cols; i++) {
		// 	console.log(i + " " + colToClauseVar[i]);
		// }



		var cur_x = var_col;
		for (var i = 0; i < v; i++) {

			tempGrid[var_row][cur_x] = this.smartAdd(tempGrid[var_row][cur_x], VARIABLE[0]);
			if (i == 0) { // The first variable should be the start variable
			  tempGrid[var_row][cur_x] = START;
			}

			/*                 
			*             VARIABLE
			*                 |
			*         ________J
			*        |
			*        |_______________
			*             |      |  |
			*           CLAUSE   C  C
			* */
			var x = cur_x;
			var y = var_row + 1;

			// draw out the shape of the path
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_DROP);
			y++;
			for (var j = 0; j < 2*i; j++) {
			  tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_D);
			  y++;
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_UL);
			x--;
			for (; x > 2*i + 1; x--) {
			  tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_L);
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_DR);
			y++;
			for (; y < clause_row - 2*i - 1; y++) {
			  tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_D);
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_UR);
			x++;
			for (; x < clause_col + 6*c + 2*v - 2*i - 1; x++) {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_R);

				// If this lines up with a corresponding clause, do it
				var potentialVar = colToClauseVar[x],
					curVar;
				if (i%2) { // negate
					curVar = -(i+1)/2;
				}
				else {
					curVar = i/2 + 1;
				}
				if (potentialVar == curVar) { // down path to clause
					tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_T);
					for (var j = y+1; j < clause_row; j++) {
						tempGrid[j][x] = this.smartAdd(tempGrid[j][x], PATH_D);
					}
				}
			} 


			if (i == v-1) { // If at last variable, draw path to the Check Clauses
			  tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_T_INV);
			  var x_tmp = x+1;
			  for (; x_tmp < cols - 1; x_tmp++) {
			      tempGrid[y][x_tmp] = this.smartAdd(tempGrid[y][x_tmp], PATH_R);                	
			  }
			  tempGrid[y][x_tmp] = this.smartAdd(tempGrid[y][x_tmp], ELBOW_DL);
			  var y_tmp = y+1;
			  for (; y_tmp < rows - 1; y_tmp++) {
			  	tempGrid[y_tmp][x_tmp] = PATH_DROP;
			  }
			  tempGrid[y_tmp][x_tmp] = ELBOW_T_INV;
			  x_tmp--;
			}
			else { // draw a path to go back up to the next variable
			  tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_UL);
			  y--;
			}
			for (; y > 2*i; y--) {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_U);            	
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_DL);
			x--;
			for (; x > var_col + i*3; x--) {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_L);            	            	
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_DR);
			y++;
			for (; y < var_row - 1; y++) {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_D);            	            	            	
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_DROP);

			// Do the same thing for the next variable (the Not version)
			// this is almost copy-pasted from above
			cur_x++;
			tempGrid[var_row][cur_x] = this.smartAdd(tempGrid[var_row][cur_x], VARIABLE[1]);

			x = cur_x;
			y = var_row + 1;
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_DROP);
			y++;
			for (var j = 0; j < 2*i + 1; j++) {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_D);
				y++;
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_UL);
			x--;
			for (; x > 2*i + 2; x--) {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_L);
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_DR);
			y++;
			for (; y < clause_row - 2*i - 2; y++) {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_D);
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_UR);
			x++;
			for (; x < clause_col + 6*c + 2*v - 2*i - 2; x++) {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_R);
			}

			if (i == v-1) { // Go from last variable to check clauses
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_T_INV);
				var x_tmp = x+1;
				for (; x_tmp < cols - 2; x_tmp++) {
				  tempGrid[y][x_tmp] = this.smartAdd(tempGrid[y][x_tmp], PATH_R);                	
				}
				tempGrid[y][x_tmp] = this.smartAdd(tempGrid[y][x_tmp], ELBOW_DL);
				var y_tmp = y+1;
				for (; y_tmp < rows - 1; y_tmp++) {
					tempGrid[y_tmp][x_tmp] = PATH_DROP;
				}
				tempGrid[y_tmp][x_tmp] = ELBOW_T_INV;
				x_tmp--;
				// Wrap around the bottom
				for (; x_tmp > 1; x_tmp--) {
					tempGrid[y_tmp][x_tmp] = this.smartAdd(tempGrid[y_tmp][x_tmp], PATH_L);
				}
				tempGrid[y_tmp][x_tmp] = this.smartAdd(tempGrid[y_tmp][x_tmp], ELBOW_UR);
				y_tmp--;
				tempGrid[y_tmp][x_tmp] = this.smartAdd(tempGrid[y_tmp][x_tmp], ELBOW_DR);
				x_tmp++;
				for (; x_tmp < clause_col; x_tmp++) {
				  tempGrid[y_tmp][x_tmp] = this.smartAdd(tempGrid[y_tmp][x_tmp], PATH_R);
				}
			}
			else {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_UL);
				y--;
			}
			//

			for (; y > 2*i + 1; y--) {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_U);            	
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_DL);
			x--;
			for (; x > var_col + i*3 + 1; x--) {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_L);            	            	
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], ELBOW_DR);
			y++;
			for (; y < var_row - 1; y++) {
				tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_D);            	            	            	
			}
			tempGrid[y][x] = this.smartAdd(tempGrid[y][x], PATH_DROP);            
			//
			cur_x++;

			tempGrid[var_row][cur_x] = this.smartAdd(tempGrid[var_row][cur_x], EMPTY);
			cur_x++;
		}

		// Add Clauses

		for (var i = 0; i < this.clauseList.length; i++) {
		var y = clause_row;
			var x = clause_col + 6 * i;
			for (var j = 0; j < 6; j++) {
				for (var k = 0; k < 2; k++) {
					tempGrid[y+k][x+j] = this.smartAdd(tempGrid[y+k][x+j], CLAUSE[k][j]);
				}; // actual clause
			}
		}
		tempGrid[clause_row+1][clause_col + 6*this.clauseList.length] = FINISH;

		var ans = [];
		for (var i = 0; i < rows; i++) {
		  ans.push([]);
		  for (var j = 0; j < cols; j++) {
			  ans[i].push(tempGrid[i][j]);
		  }
		}

		return ans;
	}
}


function parse(satInput) {
	// satInput is a string
	var clauses = [];

	var ors = satInput.split("&");
	var vars = [];
	for (var z = 0; z < ors.length; z++) {
		var x = ors[z];

	    // if (x === "&") // why would this happen
	    // 	break;
	    var clause = new Clause();

	    var start = x.indexOf('(');
	    var stop = x.indexOf(')');
	    x = x.substring(start + 1, stop);

	    var varExps = x.split("|");

	    var counter = 0;
	    for (var i = 0; i < varExps.length; i++) {
			var y = varExps[i];
			if (y === "|")
				continue;
			if (y.charAt(0) == '!') {
				clause.varNegs[counter] = false;
				y = y.substring(1, y.length);
			}
			if (vars.indexOf(y) >= 0) {
				clause.vars[counter] = vars.indexOf(y);
			}
			else {
				vars.push(y);
				clause.vars[counter] = vars.length - 1;
			}
			counter++;
	    }
	    clauses.push(clause);
	}
	return {numVars: vars.length, clauses: clauses};
}


var myStr = "(A|B|!C)&(!A|!B|!C)"
var parsed = parse(myStr);
var numVars = parsed.numVars;
var clauses = parsed.clauses;

// console.log(numVars);
// console.log(clauses);

var abstractMapGrid = new AbstractMapGrid(clauses, numVars);
var mySymbolicGrid = abstractMapGrid.getSymbolicGrid();
// console.log(mySymbolicGrid);

// for (var i = 0; i < mySymbolicGrid.length; i++) {
// 	console.log(mySymbolicGrid[i].toString());
// }