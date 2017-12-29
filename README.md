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

* ***DoubleParentEvolution*** - Two chromosomes will be selected from the population (the greater the fitness, the greater the chance of being selected to reproduce). The selected chromosomes will reproduce using crossover (selecting genes from both parents, mixing them to form it's own set of genes). Finally, the child will be mutated to provide a level of randomness to the population ***(this prevents the population reaching a local maximum)***. This process is repeated enough times to completely replace a population with it's own children.

### Fitness

The fitness is the value used to evaluate a chromosome, it will then be used to either determine if the chromosome will replace its parents or to simply decide if a chromosome will be selected to reproduce.<br>
This is calculated by implementing the interface "FitnessEvaluator" located under src/genetic/, the scoring function and the maximum score must be implemented for the system to work correctly.

### Factories

The factories provided are used to create new/random chromosomes and genes, implement your own using the interfaces to match whatever gene and chromosome your implementation requires.

### Strategies
The strategies provided are the population's behaviour, implement your own to match your project's requirements.<br>

*  ***Selection*** - Selects two parent chromosomes, based on their fitness.<br>
*  ***Crossover*** - Generates a child, based on the parent's genes.<br>
*  ***Mutation*** - Mutates the chiild, depending on the mutation rate and the gene's type.<br>
*  ***Evolution*** - The evolution's behaviour, refer back to <b>Evolution Types</b> earlier in this file.<br>
  
  
  ## String search example
  The provided example is a string search implementation using a genetic algorithm, when given a target string, it will
  generate chromosomes and evolve the population until one chromosome matches the target (100% fitness).
  
  For more information on creating genetic algorithms, visit: <br>
  * [Springer book on evolutionary algorithms](http://www.springer.com/cda/content/document/cda_downloaddocument/9780387221960-c1.pdf?SGWID=0-0-45-166514-p46178519)<br>
  * [Daniel Shiffman's genetic algorithm video series](https://www.youtube.com/watch?v=9zfeTw-uFCw&list=PLRqwX-V7Uu6bJM3VgzjNV5YxVxUwzALHV)<br>
  
  Inspired by:<br>
  * [Roger Asling's Evolution of Mona Lisa](https://rogerjohansson.blog/2008/12/07/genetic-programming-evolution-of-mona-lisa/)<br>

