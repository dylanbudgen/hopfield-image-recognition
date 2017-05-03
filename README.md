## Hopfield network with image recognition and correction

This is a project as part of my computer science course. I was assigned to create a Hopfield network with text pattern inputs. I decided to enhance that and implement image recognition and correction. 


### Hopfield network

The Hopfield network uses the Hebbian learning rule with asynchronous updating of each node. There is also a learnability check, to ensure the input patterns are learnable.


### How to use

The program requires a text file listing the pathways to the images to be trained and the corrupted images to be fixed.

The text files should be laid out like the following:

```
example_images/100_stored_1.png
example_images/100_stored_2.png
example_images/100_stored_3.png
example_images/100_stored_4.png
```

```
example_images/100_corrupt_1.png
example_images/100_corrupt_2.png
example_images/100_corrupt_3.png
example_images/100_corrupt_4.png
```

There are example images and input files, with both 16x16 images and 100x100 images. 

The program is run on commandline with the following syntax:  
```
java Main "pathway to the training images" "pathway to the corrupt images"
```
That said, there is a folder with example images and example text files. The main class has the code for these commented out for your use.


### Important note on the image files
The images must be square and have the same dimensions for height and width. The pixels must also be either pure white or pure black. Any anti-aliasing will most likely ruin the output.


### Known bugs
Currently the maximum image size I have been able to successfully run the program with, without a heap overflow, is 100 by 100 pixels. The image processing almost certainly need optimisation. 


### Contact me
Thanks for taking the time to look at my project. If you wish to contact me, please email me at ***REMOVED***
