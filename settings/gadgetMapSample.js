console.log("Loaded gadgetMapSample.js");

var sampleGadgetMap = 
[[0,0,0,0,0,0,0,63,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,62,0,0],
[0,0,0,0,0,0,0,51,63,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,62,50,0,0],
[0,0,0,0,0,0,0,51,51,0,63,52,52,52,52,52,52,52,52,52,52,52,52,52,62,50,50,0,0],
[0,0,0,0,0,0,0,51,51,0,51,63,52,52,52,52,52,52,52,52,52,52,52,62,50,50,50,0,0],
[0,0,0,0,0,0,0,51,51,0,51,51,0,63,52,52,52,52,52,52,52,52,62,50,50,50,50,0,0],
[0,0,0,0,0,0,0,51,51,0,51,51,0,51,63,52,52,52,52,52,52,62,50,50,50,50,50,0,0],
[0,0,0,0,0,0,0,54,54,0,54,54,0,54,54,0,0,0,0,0,0,50,50,50,50,50,50,0,0],
[0, 0, 0, 0, 0,0,0,70,22,123,21,22,123,21,22,123,0,0,0,0,0,50,50,50,50,50,50,0,0],
[0, 0, 0, 0, 0, 0, 0, 54,54,  0,54,54,  0,54,54,  0,0,0,0,0,0,50,50,50,50,50,50,0,0],
[0,63,52,52,52,52,52, 60,51, 0, 51,51, 0, 51,51,0,0,0,0,0,0,50,50,50,50,50,50,0,0],
[0,51,63,52,52,52,52,52,60,0,51,51,0,51,51,0,0,0,0,0,0,50,50,50,50,50,50,0,0],
[0,51,51,63,52,52,52,52,52,52,60,51,0,51,51,0,0,0,0,0,0,50,50,50,50,50,50,0,0],
[0,51,51,51,63,52,52,52,52,52,52,60,0,51,51,0,0,0,0,0,0,50,50,50,50,50,50,0,0],
[0,51,51,51,51,63,52,52,52,52,52,52,52,60,51,0,0,0,0,0,0,50,50,50,50,50,50,0,0],
[0,51,51,51,51,51,63,52,52,52,52,52,52,52,60,0,0,0,0,0,0,50,50,50,50,50,50,0,0],
[0,51,51,51,51,51,51,0,0,0,0,0,0,0,0,0,0,0,0,0,0,50,50,50,50,50,50,0,0],
[0,51,51,51,51,51,61,53,53,53,53,53,53,53,53,53,53,53,53,53,53,65,10,10,10,10,10,62,0],
[0,51,51,51,51,61,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,65,10,10,10,10,54,62],
[0,51,51,51,61,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,60,50,50,50,54,54],
[0,51,51,61,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,60,50,50,54,54],
[0,51,61,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,60,50,54,54],
[0,61,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,60,54,54],
[0,0 , 0, 0, 0, 0, 0, 0, 0,31,32,33,34,35,36,31,32,33,34,35,36, 0,0,0,0,0,0,    54,54],
[0,63,53,53,53,53,53,53,53,37,38,39,40,41,42,37,38,39,40,41,42,80,0,0,0,0,0,    54,54],
[0,61,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,65,65]]

var gadgetDescriptions = [
  [
      "7777777......7777777",
      "7.....7......7.....7",
      "7.....7......7.....7",
      "7.....7......7.....7",
      "7.....7......7.....7",
      "7777777......7777777",
      "7...................",
      "7...................",
      "777777777777777777..",
      "7...................",
      "7...................",
      "7..............77777",
      "7..............7...7",
      "7..............7...7",
      "7..............7...7",
      "7..............7...7",
      "7..............7...7",
      "7..............7...7",
      "7..............7...7",
      "7..............77777"
  ],
  [   "7777777......7777777",
      "7777777......7777777",
      "7.....7.........7..7",
      "7.....7.........7..7",
      "777777777777....7..7",
      "..........77....7...",
      "..........77....7...",
      "..........77....7...",
      ".......1..77..1.7...",
      "77777777..77..777...",
      "7...............7...",
      "7...............7..7",
      "7...............7..7",
      "7...............77.7",
      "7777............7..7",
      "7..6......77....6..7",
      "7..77777777777777777",
      "7.............7....7",
      "7.............7....7",
      "7777777.......777777"],
  ["7777777.....77777777",
      "......7......7.....7",
      "......77.....7.....7",
      "......7......7.....7",
      "7777777.....77777777",
      "......7......7......",
      "......77.....7......",
      "......7......7......",
      "......7.....77......",
      "......7......7......",
      "......77.....7......",
      "......7......7...5..",
      "......76666667......",
      "......7......7......",
      "..3.7..........7....",
      "77777776666667777777",
      "......7......7.....7",
      "......7.....77.....7",
      "......7......7.....7",
      "77777777.....7777777"
  ],
  [   
      "7777777......7777777",
      "7.....7.....77.....7",
      "7.....7......7.....7",
      "7.....77.....7.....7",
      "7.....7......7.....7",
      "7.....7.....77.....7",
      "7.....7......7.....7",
      "7.....77.....7.....7",
      "7.....7......7.....7",
      "7.....7.....77.....7",
      "7.....7......7.....7",
      "7.....77.....7.....7",
      "7.....7......7.....7",
      "7.....7.....77.....7",
      "7.....7......7.....7",
      "7.....77.....7.....7",
      "7.....7......7.....7",
      "7.....7.....77.....7",
      "7.....7......7.....7",
      "7777777......7777777"],
  [
    "77777777777777777777",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "77777777777777777777",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "77777777777777777777"
    ],
  [
    "7777777......7777777",
    "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
        "7.....7......7.....7",
    "7777777......7777777"
    ],
  [
      "77777777.....7777777",
      "7.....7......7.....7",
      "7.....7.....77.....7",
      "7.....7......7.....7",
      "7.....77.....7.....7",
      "7.....7......7.....7",
      "7777777.....77.....7",
      "......7......7.....7",
      "......77.....7.....7",
      ".............7.....7",
      "............77.....7",
      ".............7.....7",
      ".............7.....7",
      "7............7.....7",
      "77777777777777.....7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "77777777777777777777"
  ],
  [
      "77777777777777777777",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "77777777777777.....7",
      ".............7.....7",
      ".............7.....7",
      ".............7.....7",
      ".............7.....7",
      ".............7.....7",
      ".............7.....7",
      "77777777.....7.....7",
      "7.....7......7.....7",
      "7.....7.....77.....7",
      "7.....7......7.....7",
      "7.....77.....7.....7",
      "7.....7......7.....7",
      "7777777.....77.....7"
  ],
  [
      "77777777777777777777",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "77777777777777777777",
      "....................",
      "....................",
      "....................",
      "....................",
      "....................",
      "....................",
      "7777777......7777777",
      "7.....7.....77.....7",
      "7.....7......7.....7",
      "7.....77.....7.....7",
      "7.....7......7.....7",
      "7.....7.....77.....7",
      "7777777......7777777"
  ],
  [
      "7777777......7777777",
      "7.....7.....77.....7",
      "7.....7......7.....7",
      "7.....77.....7.....7",
      "7.....7......7.....7",
      "7.....7.....77.....7",
      "7777777......7777777",
      "......77............",
      "....................",
      ".............7......",
      "....................",
      "......7.............",
      "....................",
      "77777777777777777777",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "77777777777777777777"],
  ["7...................",
      "7...................",
      "77..................",
      "7...................",
      "7.....77777777777777",
      "7.....7............7",
      "77....7............7",
      "7.....7............7",
      "7....77............7",
      "7..................7",
      "7.....1............7",
      "7777777............7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..77777777777777777",
      "7..6................",
      "77777777777777777777"],
  ["...................7",
      "...........7.......7",
      ".......7...........7",
      "...................7",
      "77777777777777777777",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "7..................7",
      "77777777777777777777"],
  ["77777777777777777777",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "....................",
    "77777777777777777777",
    "....................",
    "....................",
    "....................",
    "77777777777777777777"],
  ["77777777777777777777",
      "..............7.....",
      "..............7.....",
      "..............7.....",
      "77777777......7.....",
      ".......7......7.....",
      ".......7......7.....",
      ".......7......7.....",
      ".......7......7.....",
      ".......7......7.....",
      ".......7......7.....",
      ".......7......7.....",
      ".......7......7.....",
      ".......6......6.....",
      ".......6......6.....",
      "77777777777777777777",
      "7..................7",
      "7..................7",
      "7..................7",
      "77777777777777777777"],
  [
    "77777777777777777777",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "7..................7",
    "77777777777777777777"
  ],
  [
      "7777777......7777777",
      "7.....7......7.....7",
      "7.....7......7.....7",
      "7.....7...5..7.....7",
      "7.....7......7.....7",
      "7777777......7777777",
      "7...................",
      "7...................",
      "777777777777777777..",
      "7...................",
      "7...................",
      "7..............77777",
      "7..............7...7",
      "7..............7...7",
      "7..............7...7",
      "7..............7...7",
      "7..............7...7",
      "7..............7...7",
      "7..............7...7",
      "7..............77777"
  ],
];

var gadgetNames = ["VARIABLE_L", "CROSSOVER_DR", "CROSSOVER_UR", "PATH_UD", "PATH_RL", "PATH_DROP", "ELBOW_UL",
  "ELBOW_DL", "ELBOW_T", "ELBOW_T_INV", "CLAUSE_A", "CLAUSE_B", "CLAUSE_C", "CLAUSE_D", "EMPTY", "START"];

var gadgetIdToName = {
	21 : "VARIABLE_L",
	22 : "VARIABLE_L",

  50 : "PATH_UD",
  51 : "PATH_UD",
  52 : "PATH_RL",
  53 : "PATH_RL",

	54 : "PATH_DROP",

	60 : "ELBOW_UL",
	61 : "ELBOW_UL",
	62 : "ELBOW_DL",
	63 : "ELBOW_DL",

	64 : "ELBOW_T",
	65 : "ELBOW_T_INV",

	10 : "CROSSOVER_UR",
	11 : "CROSSOVER_UR",
	12 : "CROSSOVER_DR",
	13 : "CROSSOVER_DR",

  31 : "CLAUSE_A",
  32 : "CLAUSE_B",
  33 : "CLAUSE_A",
  34 : "CLAUSE_B",
  35 : "CLAUSE_A",
  36 : "CLAUSE_B",

  37 : "CLAUSE_C",
  38 : "CLAUSE_C",
  39 : "CLAUSE_C",
  40 : "CLAUSE_C",
  41 : "CLAUSE_C",
  42 : "CLAUSE_D",

  0  : "EMPTY",
  70 : "START",
	80 : "EMPTY" // TODO: this should be FINISH
}

console.log("Number of descriptions: " + gadgetDescriptions.length);
console.log("Number of names: " + gadgetNames.length);

var gadgetIdToFlip = {
	21 : false,
	22 : true,

  50 : false,
  51 : false,
  52 : false,
  53 : false,

	54 : false,

	60 : false,
	61 : true,
	62 : false,
	63 : true,

	64 : true,
	65 : true,

	10 : false,
	11 : true,
	12 : false,
	13 : true,

  31 : false,
  32 : false,
  33 : false,
  34 : false,
  35 : false,
  36 : false,

  37 : false,
  38 : false,
  39 : false,
  40 : false,
  41 : false,
  42 : false,

  0  : false,
  70 : false,
	80 : false
}

var gadgetNameToId = {};
for (var key in gadgetIdToFlip) {
  gadgetNameToId[gadgetIdToFlip[key]] = key;
}

// Question Block holds Mushroom
// 
spriteCodeToName = {
  0: "Mario-Unused", 
  1: "Koopa", 
  2: "Koopa shell-Unused", 
  3: "Goomba", 
  4: "Mushroom", 
  5: "QuestionBlock",
  6: "Brick", 
  7: "Stone", 
};
// TODO:
// Make Super Mario and Crouch Mario occur properly
// TODO: Make Question blocks work
// TODO: Koopa to Koopa Shell


// var GADGET_ROWS = 20;
// var GADGET_COLS = 20;
var GADGET_SIDE_LEN = 20;
var BLOCK_SIDE_LEN = 8;

function getOutputObject(name) {
  output = {}
  if (name == "QuestionBlock") {
    output.thing = "Block";
    output.contents = "Mushroom";
  }
  else {
    output.thing = name;
  }
  output.width = BLOCK_SIDE_LEN;
  output.height = BLOCK_SIDE_LEN;
  return output
}

function macroGadget(reference, prethings, area, map, scope) {
  // console.log("macroGadget");
  // console.log(arguments.callee.caller.name);
  var gadgetName = reference.gadget;
  var idx = gadgetNames.indexOf(gadgetName);
  var tmpGadgetDesc = gadgetDescriptions[idx];

  if (reference.reflect) {
    var gadgetDesc = reflect(tmpGadgetDesc);
  }
  else {
    var gadgetDesc = tmpGadgetDesc;
  }

  // console.log("Index: " + idx);
  // console.log(gadgetName);
  var outputs = [];
  for (var i = 0; i < gadgetDesc.length; i++) {
    var dy = gadgetDesc.length - i - 1; // Don't make gadgets upsides-down!
    for (var dx = 0; dx < gadgetDesc[0].length; dx++) {

      if (gadgetDesc[i].charAt(dx) == '.') 
      // if (gadgetDesc[i].charAt(dx) == '.' && !(dy == 10 && dx == 10) && (dy != 0) && (dx != 0) && (dx != 19) && (dy != 19)) 
        continue;
      var code = gadgetDesc[i][dx] - '0';
      // if (dy == 10 && dx == 10) {
      //   code = 5;
      // }
      // if (dx == 0 || dy == 0 || dx == 19 || dy == 19) {
      //   code = 6;
      // }
      var output = getOutputObject(spriteCodeToName[code]);
      output.x = reference.x + BLOCK_SIDE_LEN*dx;
      output.y = reference.y + BLOCK_SIDE_LEN*dy;

      outputs.push(output);
    }
  }
  return outputs;
}

function readGadgetMap(gadgetMap) {
  var creationCmds = [];
  var start_pos = {};
  for (var i = 0; i < gadgetMap.length; i++) {
    // var y = i;
    var y = gadgetMap.length - i - 1;
    for (var x = 0; x < gadgetMap[0].length; x++) {
      var gadgetCode = gadgetMap[i][x];
      if (gadgetCode == 123) {
        // This is a blank square
        continue;
      }
      if (gadgetCode == 80) {
        // TODO: Implement start and finish
        continue;
      }
      // if (gadgetCode >= 30 && gadgetCode <= 42) {
      //   // TODO: implement variables and clauses
      //   continue;
      // }
      var refl = gadgetIdToFlip[gadgetCode];
      var name = gadgetIdToName[gadgetCode];
      // console.log("Name " + name);
      // console.log("Gadget name is " + name + " Id " + gadgetCode);
      if (name == "START") {
        console.log("found START");
        start_pos.x = x;
        start_pos.y = y;
      }

      var cmd = {
        "macro": "Gadget",
        "gadget": name,
        "reflect": refl,
        "x": x*BLOCK_SIDE_LEN*GADGET_SIDE_LEN,
        "y": y*BLOCK_SIDE_LEN*GADGET_SIDE_LEN
      }

      creationCmds.push(cmd);
    }
  }

  var new_map = {
    "name" : "3sat",
    "locations" : [
      {"xloc": (start_pos.x + .5) * GADGET_SIDE_LEN * BLOCK_SIDE_LEN, 
       "yloc": (start_pos.y + .5) * GADGET_SIDE_LEN * BLOCK_SIDE_LEN}
      // { "entry": "Castle"}
    ],
    "areas": [  
      {
        "setting": "Castle",
        "creation": creationCmds
      }
    ],
  };
  return new_map;
}


function reflect(grid) {
  var reflected = [];
  function reverse(s) {
    return s.split('').reverse().join('');
  }
  for (var i = 0; i < GADGET_SIDE_LEN; i++) {
    reflected.push(reverse(grid[i]));
  }
  return reflected;
}


var my_map = readGadgetMap(sampleGadgetMap);
var str = JSON.stringify(my_map, null, 2);

// console.log(str);