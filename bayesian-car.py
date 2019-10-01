#Import required packages
import math
from pomegranate import *

battery =DiscreteDistribution( { 0: 0.1, 1: 0.9} )

radio =ConditionalProbabilityTable(
[[ 0, 0, 1.0 ],
[ 0, 1, 0.0 ],
[ 1, 0, 0.1 ],
[ 1, 1, 0.9 ]], [battery] )

ignition =ConditionalProbabilityTable(
[[ 0, 0, 1.0 ],
[ 0, 1, 0.0 ],
[ 1, 0, 0.05 ],
[ 1, 1, 0.9 ]], [battery] )

gas =DiscreteDistribution( { 0: 0.1, 1: 0.9} )

start =ConditionalProbabilityTable(
[[ 0, 0, 0, 1.0 ],
[ 0, 0, 1, 0.0 ],
[ 0, 1, 0, 1.0 ],
[ 0, 1, 1, 0.0 ],
[ 1, 0, 0, 1.0 ],
[ 1, 0, 1, 0.0 ],
[ 1, 1, 0, 0.01 ],
[ 1, 1, 1, 0.99 ]], [ignition, gas] )

moves =ConditionalProbabilityTable(
[[ 0, 0, 1.0 ],
[ 0, 1, 0.0 ],
[ 1, 0, 0.01 ],
[ 1, 1, 0.99 ]], [start] )

d1 = State( battery, name="battery" )
d2 = State( radio, name="radio" )
d3 = State( ignition, name="ignition" )
d4 = State( gas, name="gas" )
d5 = State( start, name="start" )
d6 = State( moves, name="moves" )
 
#Building the Bayesian Network
network = BayesianNetwork( "Solving the Car Problem With Bayesian Networks" )
network.add_states(d1, d2, d3, d4, d5, d6)
network.add_edge(d1, d2)
network.add_edge(d1, d3)
network.add_edge(d3, d5)
network.add_edge(d4, d5)
network.add_edge(d5, d6)
network.bake()

beliefs = network.predict_proba({'radio' : 1, 'gas' : 1, 'start' : 0})
beliefs = map(str, beliefs)
print("n".join( "{}t{}".format( state.name, belief ) for state, belief in zip( network.states, beliefs ) ))

true = 0
false = 0

for x in range(1, 1000):
    results = network.predict([[None, 0, None, 1, None, None]])
    if results[0][0] == 1:
        true += 1
    else:
        false += 1

print (true/(true+false))    