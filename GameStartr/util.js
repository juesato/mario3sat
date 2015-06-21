// Some helper functions used in GameStartr: scrollWindow for maintaining solids

function findStartByX(arr, searchX) {
    // Do not reuse, this is terrible. Off-by-one error likely.
    var minIndex = 0;
    var maxIndex = arr.length - 1;
    var currentIndex;
    var currentElement;
 
    while (minIndex <= maxIndex) {
        currentIndex = (minIndex + maxIndex) / 2 | 0;
        currentX = arr[currentIndex].right;
 
        if (currentX < searchX) {
            minIndex = currentIndex + 1;
        }
        else if (currentX > searchX) {
            maxIndex = currentIndex - 1;
        }
        else {
            // This should really be binary search, but I'm lazy.
            while (arr[currentIndex].right == currentX) {
                currentIndex--;
            }
            return currentIndex+1;
        }
    }
 
    return currentIndex;
}

function findEndByX(arr, searchX) {
    // Do not reuse, this is terrible. Off-by-one error likely.
    var minIndex = 0;
    var maxIndex = arr.length - 1;
    var currentIndex;
    var currentElement;
 
    while (minIndex <= maxIndex) {
        currentIndex = (minIndex + maxIndex) / 2 | 0;
        currentX = arr[currentIndex].left;
 
        if (currentX < searchX) {
            minIndex = currentIndex + 1;
        }
        else if (currentX > searchX) {
            maxIndex = currentIndex - 1;
        }
        else {
            // This should really be binary search, but I'm lazy.
            while (arr[currentIndex].left == currentX) {
                currentIndex++;
            }
            return currentIndex-1;
        }
    }
 
    return currentIndex;
}