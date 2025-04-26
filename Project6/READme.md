# JavaCoding
SomePastJavaCoding_Projects

We have seen that when searching for an item in a tree, BFS will return the sought-for item that
is closest to root. In this scenario we can imagine that the vertices in the tree are equally far
from each other. In other words, the distance from the root to a vertex is just the level of that
vertex, or alternatively the number of edges traversed to get from the root to the vertex. In this
assignment, we want to generalize our distance measure to allow for arbitrary distances—each
edge defines a distance between the two vertices it connects.
![image](https://github.com/user-attachments/assets/3715a4aa-adef-4177-9893-2ebf748f72dc)

Here we see that in the left tree if we're searching for letter 'c', BFS will return the highest level
'c' in the tree. In the case of variable distances, we want our search to return the 'c' that has the
shortest distance, where the distance of a node v is calculated by summing the numbers along
the path from root to v.
In this case, using a regular queue will not do, because the vertex that is the "fewest hops 
away" may not be the closest. Instead, we need a queue that takes the vertex distances into 
account and gives priority to the closest ones. This priority queue is exactly the min-heap we've
already used many times! So, as DFS uses a stack and BFS uses a queue, this algorithm (a
simplified version of Dijkstra's algorithm) uses a min-heap to order the vertices it explores.
![image](https://github.com/user-attachments/assets/6b0f46d9-800b-4b03-8cd2-3d6fe0c71afa)


