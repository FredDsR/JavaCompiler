def main():
    commands = []
    stack = []

    with open('./output.txt') as file:
        commands = file.read().splitlines()
   
    for command in commands:
        match command.split(' '):
            case ['PUSH', value]:
                stack.append(int(value))
            case ['PRINT']:
                print(stack.pop())
            case ['SUM']:
                stack.append(stack.pop() + stack.pop())
            case ['SUB']:
                stack.append(stack.pop() - stack.pop())
            case ['MULT']:
                stack.append(stack.pop() * stack.pop())
            case ['DIV']:
                stack.append(stack.pop() / stack.pop())
            case _:
                raise Exception("Command not found.")    

if __name__ == '__main__':
    main()
