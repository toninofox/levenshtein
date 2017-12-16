# levenshtein
The distance between two strings is given as the minimum number of operations needed to
transform one string into the other with the allowable edit operations being insertion, deletion,
or substitution of a single character.
For example, the distance between "kitten" and "sitting" is 3, since the following three edits
change one into the other, and there is no way to do it with fewer than three edits:
1. k itten → s itten (substitution of "s" for "k")
2. sitt e n → sitt i n (substitution of "i" for "e")
3. sittin → sittin g (insertion of "g" at the end).

# Problem
1. Main challenge - Develop a small program that can calculate the distance between
any 2 strings.
2. Extension - Develop a small web application that exposes this functionality as a
REST (or SOAP) web service.

# SOLUTION
Maven Multimodule web project made in JAVA 8 

The API REST module is very simple
Single POST endpoint with JSON content type. The Request DTO has the two words to compare and the boolean to configure the operation as case sensitive or not.
Tested with Jsonpath and SpringTest. Mockito has been added but not used at the end

The Core module contains the algorithm that has been used to solve this problem: Levenshtein
Created using the TDD approach

Common module is just to configure plugins and easilly add further plugins in the future and modify the behavior between any environments defined.
