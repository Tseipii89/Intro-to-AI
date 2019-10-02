#Import required packages
import math
from pomegranate import *
import numpy

earthquake = DiscreteDistribution( { 0: 110/111.0, 1: 1/111.0} )
burglar = DiscreteDistribution( { 0: 364/365.0, 1: 1/365.0} )

alarm =ConditionalProbabilityTable(
[[ 0, 0, 0,  0.9905],
[ 0, 0, 1, 0.0095 ],
[ 0, 1, 0, 0.08 ],
[ 0, 1, 1, 0.92 ],
[ 1, 0, 0, 0.19 ],
[ 1, 0, 1, 0.81 ],
[ 1, 1, 0, 0.03 ],
[ 1, 1, 1, 0.97 ]], [earthquake, burglar] )


d1 = State( earthquake, name="earthquake" )
d2 = State( burglar, name="burglar" )
d3 = State( alarm, name="alarm" )
 
#Building the Bayesian Network
network = BayesianNetwork( "Solving the earthquake Problem With Bayesian Networks" )
network.add_states(d1, d2, d3)
network.add_edge(d1, d3)
network.add_edge(d2, d3)
network.bake()

beliefs = network.predict_proba({'alarm' : 1})
beliefs = map(str, beliefs)
print("n".join( "{}t{}".format( state.name, belief ) for state, belief in zip( network.states, beliefs ) ))

true = 0
false = 0

for x in range(1, 10000):

    results =   network.predict([[1, burglar.sample(), 1]])
    if results[0][1] == 1:
        true += 1
    else:
        false += 1

print (true/(true+false))    