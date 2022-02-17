# ⋆⁺₊⋆ ☾⋆⁺₊⋆ARTEMIS⋆⁺₊⋆ ☾⋆⁺₊⋆
Artemis is a java program that helps you to manage your tasks

## Usage

### `list` - display a list of all tasks

Use this command when you want a list of all tasks you have added so far.

Example of usage: 

`list`

Expected outcome:
```
-> Here are the tasks in your list:
    1.[T][x] test1
    2.[E][ ] test2  (at: location)
    3.[D][ ] test5  (by: Feb 02 2022)
```
Description of the outcome:
```
- Artemis lists command in a chronogical order based on time you add the tasks.
- The first box of the task indicates the type of task (todo, event, deadline). 
- The second box indicates the completion. This is followed by a description of a task 
and date if applicable
```

###`todo [task descriptioon]` - add todo

Use this command when you want to add todo to the task list

Example of usage:

`todo do homework`

Expected outcome:
```
Gotcha~ I've added this task:
    [T][ ] do homework
Now you have 6 task in the list.
```
Description of the outcome:
```
Artemis adds a new todo with a description do homework to the list.

```

###`event [task description] /at [location or time] ` - add event
Use this command when you want to add event in the task. [Location or time] can be any text input.

Example of usage:

`
event birthday party /at friend's house
`

Expected outcome:
```
Gotcha~ I've added this task:
    [E][ ] birthday pary (at: friend's house)
Now you have 6 task in the list.
```
Description of the outcome:
```
Artemis adds the event titled birthday party and its location friend's house.
```

### `deadline [task description] /by [date]` - add deadline

Use this command when you want to add a deadline to the task list.
Note that the date has to be in the format of yyyy-mm-dd. Otherwise there will be an error

Example of usage:

`
deadline assignment /by 2022-02-19
`

Expected outcome:
```
Gotcha~ I've added this task:
    [D][ ] assignment (by: Feb 19 2022)
Now you have 6 task in the list.
```
Description of the outcome:
```
Artemis adds the deadline task named assingment and the due date in the format of MMM-dd-yyyy.
```

###`mark [task number]` - mark the task as complete

Use this command when you want to mark the task as complete. To find the task index type `list` and use the index given by the task list.


Example of usage:

`mark 2`

Expected outcome:
```
Nice~ I've marked this task as *done*:
    [D][X] test6  (by: June 06 2022)
```
Description of the outcome:
```
Artemis marks Task 2 (the number based on the index given in the task list).
```

### `unmark [task number]` - unmark the task

Use this command when you want to unmark the task that had been marked.

Example of usage:

`unmark 2`

Expected outcome:
```
Okie~ I've marked this task as *not done* yet:
    [T][ ] test1
```
Description of the outcome:
```
Artemis unmarks Task 2 (the number based on the index given in the task list) 
```

###`delete [task number]` - delete the task

Use this command when you want to delete the task.

Example of usage:

`delete 2`

Expected outcome:
```
Noted~ I've removed this task:
    [D][ ] test5  (by: June 06 2022)
Now you have 3 task in the list.

```
Description of the outcome.
```
Artemis deletes Task 2 (the number based on the index given in the task list).
```
###`find [term]` - find the task containing the term

Use this command to find the task that contains a certain term

Example of usage:

`find exam`

Expected outcome:
```
Here are the *matching tasks* in your list:
    [E][] CS2103T exam (at: Zoom)
    [E][] CS2101 mock-exam (at: NUS)
```
Description of the outcome.
```
Artemis lists tasks that contains a term 'exam'.
```
### `reminder` - list impending deadlines

Use this command to see a list of deadline task with impending deadlines (tasks that are due within 24 hrs)

Example of usage:

`reminder`

Expected outcome:
```
Here are the reminder 
(due within 24hr) in your list:
    [D][] CS2103T ip (by: Feb 18 2022)
    [D][] CS2105 assignment (by: Feb 19 2022)
```
Description of the outcome.
```
Artemis lists tasks that are due within 24hrs as of current date (Feb 18th 2022)
```

###`bye` - quit Artemis 

Use this command to quit Artemis

Example of usage:

`bye`

Expected outcome:
```
Artemis terminates.
```
