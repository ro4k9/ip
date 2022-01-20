# Duke Chatbot
Duke chatbot is a java program that helps you to manage your tasks

## Usage

### `list` - displays a list of all tasks

Use this command when you want a list of all tasks you have added so far.

Example of usage: 

`list`

Expected outcome:
```
  n____n
 ( o v o )
--u-----u---------------------------------
-> Here are the tasks in your list:
    1.[T][x] test1
    2.[E][ ] test2  (at: Mon 2-4pm)
    3.[D][ ] test5  (by: June 6th)
```
Description of the outcome.
```
Duke lists command in a chronogical order based on time you add the tasks.
The first box of the task indicates the type of task (todo, event, deadline). 
The second box indicates the completion. This is followed by the description of a task and date.
```

### `todo [description of the task] - add event to a list of tasks

Use this command when you want to add todo in the task.

Example of usage:

`todo do homework
`
Expected outcome:
```
   n____n
  ( ^ v ^ )
---u-----u--------------------------------
Gotcha~ I've added this task:
    [T][ ] do homework
Now you have 6 task in the list.
------------------------------------------
```
Description of the outcome.
```
Duke adds the new todo do homework to the list.

```

### `event [description of the task] /at [date] ` - add todo to a list of tasks

Use this command when you want to add event in the task.

Example of usage:

`
event birthday party /at 9:00AM
`

Expected outcome:
```
   n____n
  ( ^ v ^ )
---u-----u--------------------------------
Gotcha~ I've added this task:
    [E][ ] birthday pary (at: 9:00AM)
Now you have 6 task in the list.
------------------------------------------
```
Description of the outcome.
```
Duke adds the event titled birthday party and its date.
```

Example of usage:

`
event birthday party
`

Expected outcome:
```
   n____n
  ( ^ v ^ )
---u-----u--------------------------------
Gotcha~ I've added this task:
    [E][ ] birthday pary (at: nil)
Now you have 6 task in the list.
------------------------------------------
```
Description of the outcome.
```
If no date has been specified by /at, Duke fills the date with nil.
```
### `Deadline [description of the task] /by [date]` - add deadline to a list of tasks

Use this command when you want to add event in the task.

Example of usage:

`
deadline assignment /by Monday
`

Expected outcome:
```
   n____n
  ( ^ v ^ )
---u-----u--------------------------------
Gotcha~ I've added this task:
    [D][ ] assignment (by: Monday)
Now you have 6 task in the list.
------------------------------------------
```
Description of the outcome.
```
Duke adds the deadline assingment and the due date Monday.
```

Example of usage:

`
deadline assingment
`

Expected outcome:
```
   n____n
  ( ^ v ^ )
---u-----u--------------------------------
Gotcha~ I've added this task:
    [D][ ] assignment (at: nil)
Now you have 6 task in the list.
------------------------------------------
```
Description of the outcome.
```
If no date has been specified by /by, Duke fills the date with nil.
```
### `mark [task number]` - mark the task complete

Use this command when you want to mark the task.

Example of usage:

`mark 2`

Expected outcome:
```
   n____n
  ( > v < )n
---u--------------------------------------
Nice~ I've marked this task as *done*:
    [D][X] test6  (by: June 6th)
------------------------------------------
```
Description of the outcome.
```
Task 2 (the number is given in the list) is marked complete.
```

### `unmark [task number]` - mark the task incomplete

Use this command when you want to unmark the task.

Example of usage:

`unmark 2`

Expected outcome:
```
   n____n
  ( o _ o )
------------------------------------------
Okie~ I've marked this task as *not done* yet:
    [T][ ] test1
------------------------------------------
```
Description of the outcome.
```
Task 2 (the number is given in the list) is marked complete.
```

### `delete [task number]` - delete the task

Use this command when you want to delete the task.

Example of usage:

`delete 2`

Expected outcome:
```
   n____n
  ( o n o )
------------------------------------------
Noted~ I've removed this task:
    [D][ ] test5  (by: June 6th)
Now you have 3 task in the list.
------------------------------------------
```
Description of the outcome.
```
Task 2 (the number is given in the list) is now deleted.
```


### `bye` - quit Duke chatbot

Use this command to quit Duke chatbot

Example of usage:

`bye`

Expected outcome:
```
   n____n
n ( o v o )
------------------------------------------
~*~*Cya soon*~*~!
------------------------------------------
```
Description of the outcome.
```
Duke chatbot program terminates.
```