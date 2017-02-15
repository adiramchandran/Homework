# Movie Info Fetcher

Fetch the data from themoviedb.org using HTTP get requests.

Input

Your program should take two required inputs and a third input in certain cases:
java MovieParser <# movies> <query type> [query parameter]
The number of movies parameter specifies the number of records that should be downloaded
from https://www.themoviedb.org. Choose appropriate names for the 4 query types that you
implemented in the previous assignment. Your code should cleanly handle malformed/missing
command line parameters by returning a usage message and not crashing. There exists Java libraries
to assist in parsing command line arguments; you are free to use such libraries as long as you clearly
indicate the source of code that you are using.


Downloading, filtering, and outputting movie data

Use your API key and HTTP get requests to download information for the number of movies
specified on the command line. In general, you will need to make server requests until you have
enough movies. If the user requests more movies than there are, fetch as many movies as there are.

