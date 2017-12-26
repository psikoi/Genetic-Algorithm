# Genetic Evolution Algorithm
This project is an attempt at simulating the Darwining genetic evolution on various programmable examples.

## Components
  examples  - Implementation/usage examples (currently only includes string search).<br>
  genetic   - The genetic components and interfaces to be implemented and used.
  
### Genetic Components
<b>Gene</b>        - Single element that contains DNA information. (Ex: In string search, a gene could be a character)<br>
Chromosome  - Collection of genes, this object can also store its fitness score.<br>
GeneticPair - Pair of chromosomes, created from selecting two parent chromosomes.<br>
Population  - Collection of chromosomes, this object evolves until the fittest chromosome reaches the maximum fitness.
