import pandas as pd # Import the pandas library and assign it the alias pd
import matplotlib.pyplot as plt # Import the pyplot module from the matplotlib library and assign it the alias plt

# Read the CSV file
# The CSV file is read using pd.read_csv() and stored in the DataFrame df.
df = pd.read_csv('/Users/mohamedelzeini/Documents/Coding/Data Analyst Job/Projects/The Impacts of Working Remotely and in an Office/The Impacts of Working Remotely and in an Office Survey.csv')

# Select the columns with varied responses
# The variable response_column is set to the column names.
response_columns = ['Have you ever experienced working from home?',
                    'Do you think that working from home increases your work productivity?',
                    'Do you think that working from home prevents you from going out?',
                    'Do you think that working from home gives you more flexibility?',
                    'Do you think that working from home saves you more time?',
                    'Which work type has the potential to cause you physical problems, such as the neck, back, and leg pain?',
                    'Which work type has the potential to cause mental disorders, such as stress, anxiety, and depression?',
                    'Do you think that working from home prevents you from getting in contact with people?',
                    'Which work type keeps you focused while working?',
                    'Which work type do you prefer the most?']


# Plotting a bar chart for each question
for column in response_columns:
    # Filter the column for yes and no responses
    # The DataFrame is filtered to include only rows where the selected column has 'Yes', 'No', 'Working from home', 'Working in an office', 'A mixed mode of working' values using df[response_column].notnull().
    filtered_df = df[df[column].isin(['Yes', 'No', 'Working from home', 'Working in an office', 'A mixed mode of working'])]

    
    # Count the number of each response
    # The count of each response category is calculated using .value_counts() on the filtered DataFrame.
    response_counts = filtered_df[column].value_counts()
    
    # Plot the bar chart
    # Create a bar chart with x-values as response categories and y-values as their respective counts
    plt.bar(response_counts.index, response_counts.values)
    plt.xlabel('Responses') # Set the label for the x-axis
    plt.ylabel('Count') # Set the label for the y-axis
    plt.title(f'Distribution of Responses: {column}') # Set the title of the bar chart, dynamically using the column name
    plt.xticks(rotation=45)  # Rotate x-axis labels if needed
    plt.show() # Display the bar chart
    
    # Plot the pie chart
    # The pie chart is plotted using plt.pie(), where the values and labels are set as the count and response categories, respectively.
    # The autopct='%1.1f%%' parameter formats the percentage display on the chart.
    plt.pie(response_counts.values, labels=response_counts.index, autopct='%1.1f%%')
    plt.title(f'Distribution of Responses: {column}') # Set the title of the bar chart, dynamically using the column name
    plt.axis('equal') # Ensure an equal aspect ratio for a circular pie chart
    plt.show() # Display the chart.
