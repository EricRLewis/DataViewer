# DataViewer
This application reads in a txt file and parses through coordinate data. It displays the data on 3 graphs.
The data can be manipulated or selected through either the graphs or the data table for user readability and writeability.

The graphs can be draged around to change the sizes.

Data does not need to be simple (X,Y) values. It can have any number of variabes with any title. (files a.csv and b.csv are included as samples)
for data sets larger than two variables, there are tabs at the bottom of each graph to allow users to select the independent and dependent variables.

Points can be selected on the graph by using left mouse button to click and drag a bounding box around them. the may also be selected on the 
data table by left clicking them. Data table selection works like windows explorer to allo0w the use of ctrl and shift keys.
To deselect all points, right click anywhere in the application.

Application uses opeGL to render the graphs.
