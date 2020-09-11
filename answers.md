**1) Explain REST and RESTful**

REST is an architecture style that defines constraints to be used 
for creating web services, some of them:
- It should be a client server architecture
- It should be stateless
- Responses should be cacheable
- It should be structured in layers
- It should have a Uniform interface

A system following all the above principles is called RESTful.

**2) Explain the architectural style for the creation of a web API (client server communication, formatting language, etc.)**

The creation of web API needs some thought, and answers for questions beforehand, some of them:
- What is the purpose of the API ?
- How and by whom the API is going to be used ? the answer to this question may lead to a communication choice (websocket, http requests ..)
- Communication format (json, gzip, file ..)
- Security: Secure the data in transit by enabling TLS/SSL connections
- Authorization and authentification: the state of the art OAuth 2 or a home baked solution
- Usage: if the API will be publicly available, adding an API Gateway is strongly recommended to at least throttle incomming requests
- Cacheability: Can I put in cache the server's responses ? how long (Time to Live) ?

**3) Explain HTTP requests types when used with RESTful web (GET, PUT, POST, etc.)**

HTTP verbs:

- GET: retrieve data from a resource
- DELETE: delete a specified resource
- PUT: requests that the state of the target resource be created
        or replaced with the state defined by the message payload.
        PUT requests are idempotent.
- POST: create new resources (optionally subordinate resources)
- PATCH: update a part of a resource

**4) Explain the difference between stateless and stateful protocol. Which type of protocol is HTTP ?**

Stateful protocols are ones that hold state at any point of time 
that dependends on the request and an internal state.
On the other hand, stateless protocols are one that does not hold
any state between the requests.

HTTP is a stateless protocol.

**5) Explain the difference between a GET and a POST (like how are sent the parameters)**

GET is used to retrieve data from a specified resource and 
POST is usally used to create/update resources.

GET sends its parameters using key value pairs in the URL. 

POST sends data in the body.

**6) You can’t work out how to solve a coding problem. What do you do to find the answer?**

Usually I take few minutes, think again about the problem, try
to divide it in smaller pieces, identify patterns and
spend some time in front of whiteboard. 
 
If after this, I still don't have a solution,
I search on internet, books and technical blogs similar problems.
I ask my collegues for help and if I'm still stuck,
start over with different angle 
(forget about the previous code and try a fresh start).   
 
          

      