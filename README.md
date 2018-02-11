# PrimeFinder
PrimeFinder is an open-source Prime Number finder that outputs all found numbers in an extremely compact file. It can go as large as high as the Java long limit for the number or 9223372036854775807 bytes for the file.

The common rate is 1000 primes per second, which is about 10 kB per second. The system automatically stops if the output file is exceeding the long limit (which requires 9 exabytes) or the system has reached the number "9223372036854775807," commonly known as the Java *long* Maximum Value. At a rate of 100K numbers per second, it would still take more than a decade to complete the calculations.

This software is under the CC BY (Creative Commons Attribution 4.0 International) license, which allows you to remix, adapt, change, modify, distribute, share under commercial terms as long as credit is given and a copy of this license is provided. You can read more about that [here](https://creativecommons.org/licenses/by/4.0/) or on the provided LICENSE.txt.

If you want to access the pre-built Java ARchive file, you can go to the [releases page](https://github.com/ChlodAidanAlejandro/PrimeFinder/releases) and download a copy.

To run the JAR on it's own without modification, run the following in your command line: 
`java -jar PrimeFinder.jar <maximum number> <maximum file size>`
where `<maximum number>` is the highest number that the program will reach, and `<maximum file size>` is the largest output file size that the program will handle. If you commit an error on either argument, values will default to the Java *long* Maximum Value. If you have the wrong number of arguments, the program will not run.

If you run this on a supercomputer, I would love to know the highest you got. Maybe you can even reach the Java *long* Maximum Value. Report all issues to the issue tracker. You can also add improvements to optimize the code!
