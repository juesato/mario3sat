
    /* Map macros
    */
    
    /**
     * Sample macro with no functionality, except to console.log a listing of 
     * the arguments provided to each macro function.
     * For all real macros, arguments are listed as the keys given as members of
     * the reference object.
     * They also ignore the "x" and "y" arguments, which 
     * are the x-location and y-location of the output (and both default to 0),
     * and the "macro" argument, which is listed as their alias.
     * 
     * @alias Example
     * @param {Object} reference   A listing of the settings for this macro,
     *                             from an Area's .creation Array. This should 
     *                             be treated as static!
     * @param {Object[]} prethings   The Area's actual .creation Array, which
     *                               consists of a bunch of reference Objects.
     * @param {Area} area   The area currently being generated.
     * @param {Map} map   The map containing the area currently being generated.
     */
    function macroExample(reference, prethings, area, map, scope) {
        console.log("This is a macro that may be called by a map creation.");
        console.log("The arguments are:\n");
        console.log("Reference (the listing from area.creation):  ", reference);
        console.log("Prethings (the area's listing of prethings): ", prethings);
        console.log("Area      (the currently generated area):    ", area);
        console.log("Map       (the map containing the area):     ", map);
        console.log("Scope     (the custom scope container):      ", scope);
    }
    
    /**
     * Macro to place a single type of Thing multiple times, drawing from a
     * bottom/left corner to a top/right corner.
     * 
     * @alias Fill
     * @param {String} thing   The name of the Thing to fill (e.g. "Brick").
     * @param {Number} xnum   How many times to repeat the Thing horizontally
     *                        to the right (defaults to 1)
     * @param {Number} ynum   How many times to repeat the Thing vertically
     *                        upwards (defaults to 1)
     * @param {Number} xwidth   How many units are between the left edges of 
     *                          placed Things horizontally (defaults to 0)
     * @param {Number} yheight   How many units are between the top edges of
     *                           placed Things vertically (defaults to 0)
     * @param {Number} [x]   The x-location (defaults to 0).
     * @param {Number} [y]   The y-location (defaults to 0).
     * @example   { "macro": "Fill", "thing": "Brick",
     *              "x": 644, "y": 64, "xnum": 5, "xwidth": 8 }
     * @return {Object[]}
     */
    function macroFillPreThings(reference, prethings, area, map, scope) {
        var defaults = scope.ObjectMaker.getFullPropertiesOf(reference.thing),
            xnum = reference.xnum || 1,
            ynum = reference.ynum || 1,
            xwidth = reference.xwidth || defaults.width,
            yheight = reference.yheight || defaults.height,
            x = reference.x || 0,
            yref = reference.y || 0,
            ynum = reference.ynum || 1,
            outputs = [],
            output,
            o = 0, y, i, j;
        
        for (i = 0; i < xnum; ++i) {
            y = yref;
            for (j = 0; j < ynum; ++j) {
                output = {
                    "x": x,
                    "y": y,
                    "macro": undefined
                };
                outputs.push(EightBittr.prototype.proliferate(output, reference, true));
                o += 1;
                y += yheight;
            }
            x += xwidth;
        }
        
        return outputs;
    }
    
    /**
     * Macro to continuously place a listing of Things multiple times, from left
     * to right. This is commonly used for repeating background scenery.
     * 
     * @alias Pattern
     * @param {String} pattern   The name of the pattern to print, from the
     *                           listing in scope.settings.maps.patterns.
     * @param {Number} [x]   The x-location (defaults to 0).
     * @param {Number} [y]   The y-location (defaults to 0).
     * @param {Number} [repeat]   How many times to repeat the overall pattern 
     *                            (by default, 1).
     * @param {Number[]} [skips]   Which numbered items to skip, if any.
     * @return {Object[]}
     */
    function macroFillPrePattern(reference, prethings, area, map, scope) {
        // Make sure the pattern exists before doing anything
        if (!scope.settings.maps.patterns[reference.pattern]) {
            console.warn("An unknown pattern is referenced: " + reference);
            return;
        }
        var pattern = scope.settings.maps.patterns[reference.pattern],
            length = pattern.length,
            defaults = scope.ObjectMaker.getFullProperties(),
            repeats = reference.repeat || 1,
            xpos = reference.x || 0,
            ypos = reference.y || 0,
            outputs = [],
            o = 0,
            skips = {},
            output, prething, i, j;
        
        // If skips are given, record them in an Object for quick access
        if (typeof reference.skips !== "undefined") {
            for (i = 0; i < reference.skips.length; i += 1) {
                skips[reference.skips[i]] = true;
            }
        }
        
        // For each time the pattern should be repeated:
        for (i = 0; i < repeats; i += 1) {
            // For each Thing listing in the pattern:
            for (j = 0; j < length; j += 1) {
                // Don't place if marked in skips
                if (skips[j]) {
                    continue;
                }
                
                prething = pattern[j];
                output = {
                    "thing": prething[0],
                    "x": xpos + prething[1],
                    "y": ypos + prething[2]
                };
                output.y += defaults[prething[0]].height;
                
                if (prething[3]) {
                    output["width"] = prething[3];
                }
                
                outputs.push(output);
                o += 1;
            }
            xpos += pattern.width;
        }
        
        return outputs;
    }


    function macroBridge(reference) {
        var x = reference.x || 0,
            y = reference.y || 0,
            width = reference.width,
            output = [];

        for (var i = 0; i < 10; i++) {
            output.push({
                "thing": "Stone",
                "x": x + i*20,
                "y": y,
                "width": 10,
                "height": 10
            });            
        }

        return output;
    }