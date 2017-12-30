# Genetic Evolution Algorithm
***Author: Ruben Amendoeira***<br>
This project is an attempt at simulating the Darwining genetic evolution on various programmable examples.



## Components
  * ***examples***  - Implementation/usage examples (currently only includes string search).<br>
  * ***genetic*** - The genetic components and interfaces to be implemented and used.
  
  
  
### Genetic Components
* ***Gene*** - Single element that contains DNA information. (Ex: In string search, a gene could be a character)<br>
* ***Chromosome*** - Collection of genes, this object can also store its fitness score.<br>
* ***GeneticPair*** - Pair of chromosomes, created from selecting two parent chromosomes.<br>
* ***Population*** - Collection of chromosomes, this object evolves until the fittest chromosome reaches the maximum fitness.



### Evolution Types
* ***SingleParentEvolution*** - A chromosome will create and mutate a copy of itself (child), if the child's fitness is greater than the parent's fitness, the child will replace the parent.<br>

* ***DoubleParentEvolution*** - Two chromosomes will be selected from the population (the greater the fitness, the greater the chance of being selected to reproduce). The selected chromosomes will reproduce using crossover (selecting genes from both parents, mixing them to form it's own set of genes). Finally, the child will be mutated to provide a level of randomness to the population <b>(this prevents the population reaching a local maximum)</b>. This process is repeated enough times to completely replace a population with it's own children.



### Fitness

The fitness is the value used to evaluate a chromosome, it will then be used to either determine if the chromosome will replace its parents or to simply decide if a chromosome will be selected to reproduce.<br>
This is calculated by implementing the interface "FitnessEvaluator" located under src/genetic/, the scoring function and the maximum score must be implemented for the system to work correctly.



### Factories

The factories provided are used to create new/random chromosomes and genes, implement your own using the interfaces to match whatever gene and chromosome your implementation requires.



### Strategies
The strategies provided are the population's behaviour, implement your own to match your project's requirements.<br>

*  ***Selection*** - Selects two parent chromosomes, based on their fitness.<br>
*  ***Crossover*** - Generates a child, based on the parent's genes.<br>
*  ***Mutation*** - Mutates the child, depending on the mutation rate and the gene's type.<br>
*  ***Evolution*** - The evolution's behaviour, refer back to <b>Evolution Types</b> earlier in this file.<br>

  
  <br><br>
  ## String search example
  The provided example is a string search implementation using a genetic algorithm, when given a target string, it will
  generate chromosomes and evolve the population until one chromosome matches the target (100% fitness).
  
  
  <br><br>
  ## Mona Lisa image recreation example
  The Mona Lisa example attempts to recreate a 200x200 pixel image of the Mona Lisa painting using the genetic algorithm library.
  This is done by using the single parent evolution method (Hill Climbing algorithm).<br>
  First, a random selection of polygons is created, with random points and random colors, then an offspring (child) of that chromosome is created, copying the parent's DNA, then mutated, depending on the ***mutation strength***, it is then compared to the target image using the fitness evaluator, if the child's fitness is greater than the parent's fitness, the child will replace the parent, to be possibly replaced by its own child in the future generation.
  
  ### Fitness Evaluator (Mona Lisa)
  The fitness for this example is evaluated by the sum of the absolute difference in color, for every pixel, between the target image and the fittest chromosome. It is then converted into a 0-1 float using the maximum fitness (765 * width * height).
  
  ### Mutation Strategy (Mona Lisa)
  The mutation strategy for the mona lisa example selects one random gene and mutates it depending on a variable named ***mutation strength***, this variable can have 5 values:
  * ***HARD*** - This strength will change the gene's R,G,B,A values by random (0-255) and will also select a random point from it's polygon, and change it's x and y position to a random (0-sceenSize).<br>
  * ***MEDIUM*** - This strength will change ***ONE*** of the gene's variables, it has a 50% percent chance to change one of the color values, by a random (0-255) and a 50% chance of changing on of the x,y values by a random (0-screenSize).<br>
  * ***SOFT*** - This strength is the same as the above MEDIUM, except it adds/subtracts the value by a small delta, instead of randomly. This small delta can be between -25 and 25.<br>
  * ***GAUSSIAN*** - This strength is the same as the above SOFT, and was an attempt of using the gaussian bell curve to calculate better delta values, this unfortunately proved to be worse than soft mutation, but was worth the try.<br>
  * ***OPTIMAL*** - This strength is an improved mutation strategy, combining almost all of the previous mutation strengths, to get the best results in the short and long term.
  
  ***How optimal mutation works***<br>
  After running each mutation strength multiple times in various timed sessions, I gathered that the Hard mutation works great for short-term results, but fails to evolve correctly in the long term. Opposite to hard mutation, soft mutation worked the best for long sessions but took a while to get to a high fitness. To take advantage of both results, I created the optimal mutation, that starts out hard, after ***5 seconds*** switches to medium, after ***60 seconds*** switches to soft, and stays at soft for the rest of the session.
However, the optimal mutation also decreases the soft delta (initially -25 to 25) by 1 every 3 minutes, and attempts to add a completely random gene every 10 seconds (to introduce some more radical mutations, to hopefully prevent local maximum).

This is the data I collected, that helped me make those decisions:<br>

![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/15%20sec%20graph.png)<br><br>
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/5%20min%20graph.png)

<br><br>
### Results (1h session, 50 genes (polygons) )

![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/2826.png)
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/4386.png)
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/7937.png)
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/8892.png)
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/17240.png)
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/22668.png)
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/41432.png)
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/45166.png)
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/100438.png)
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/178229.png)
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/375870.png)
![](https://github.com/psikoi/Genetic-Evolution-Algorithm/blob/master/results/images/571027.png)

  
  <br><br>
  
  
  For more information on creating genetic algorithms, visit: <br>
  * [Springer book on evolutionary algorithms](http://www.springer.com/cda/content/document/cda_downloaddocument/9780387221960-c1.pdf?SGWID=0-0-45-166514-p46178519)<br>
  * [Daniel Shiffman's genetic algorithm video series](https://www.youtube.com/watch?v=9zfeTw-uFCw&list=PLRqwX-V7Uu6bJM3VgzjNV5YxVxUwzALHV)<br>
  
  Inspired by:<br>
  * [Roger Asling's Evolution of Mona Lisa](https://rogerjohansson.blog/2008/12/07/genetic-programming-evolution-of-mona-lisa/)<br>

