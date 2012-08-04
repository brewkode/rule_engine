=== Rule Engine ===

A simple system which can work and process work items based on rules define for them.
The idea was to alieviate the pain of site specific parsing to extract relevant content
Three types of rules can be defined.
- CSS based extraction
- Regex based extraction
- Executing JS on the HTML to extract content ( Not done yet - WIP)

The Rule engine would return results based on the best fit for a particular URL
For ex., if Rules were defined as below:

*:
    title: h1
    comments: div#comments, div#comment, div#user_comment
    user: div#comment span.user

xyz.com: 
    title: div#name
    comments: div#comment_bar span.comments


Now, if a user wanted to scrape title, comments & user for xyz.com
the title, comments would xyz.com would take effect. And, the rule for user
at * will take effect. (WIP)

