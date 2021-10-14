A command line banking app. 

Limitations and Vulnerabilities:

- during login, if a customer has the same name and password as another customer,
it's possible the customer will log in to the wrong account

- in the customer interface, the customer has access to other customer accounts. 
Although accessing them would be unlikely as the interface only displays 
customer's bank accounts, no others. 

- in the customer interface, the customer can access inactive accounts if they know 
their id. Again, unlikely as the id is never presented to them until the acccount is active

- Encryption of password only reverses the password. The Encryption class is it's own file
though so updating it to perform a standard of encryption shouldn't be too much more work


Accomplishments:

- If a manager deletes a customer profile, a sql reference error will not occur 
b/c app will first delete all bank accounts associated with customer before it removes the 
customer profile

- A customer must login to interact with the app. Even after customer creates profile, user
is unable to access customer interface until they successfully login

- Code should be somewhat painless to refactor as it is organized into intuitive segments 
that most people should be able to follow aka not spaghetti code (I hope).
