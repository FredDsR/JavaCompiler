use std::fs;

fn main() {
    let code = get_file();
    let result = run(code);
    println!("Result: {result}");
}

fn get_file() -> String {
    let filepath = "../output.txt";

    let content = fs::read_to_string(filepath)
        .expect("Cannot read the file: {filepath}");

    return content;
}

fn sum(stack: &mut Vec<i32>){
    let value1 = stack.pop().expect("Cannot pop item.");
    let value2 = stack.pop().expect("Cannot pop item.");
    stack.push(value1 + value2);
}

fn sub(mut stack: Vec<i32>){
    let value1 = stack.pop().expect("Cannot pop item.");
    let value2 = stack.pop().expect("Cannot pop item.");
    stack.push(value1 - value2);
}

fn mult(mut stack: Vec<i32>){
    let value1 = stack.pop().expect("Cannot pop item.");
    let value2 = stack.pop().expect("Cannot pop item.");
    stack.push(value1 * value2);
}

fn div(mut stack: Vec<i32>){
    let value1 = stack.pop().expect("Cannot pop item.");
    let value2 = stack.pop().expect("Cannot pop item.");
    stack.push(value1 / value2);
}

fn run(code: String) -> i32{
    
    let mut stack: Vec<i32> = Vec::new();
    
    let lines = code.split("\n");

    for line in lines {
        let command: Vec<&str> = line.split(" ").collect();
        match command[0] {
            "SUM"  => sum(&mut stack),
            "SUB"  => sub(stack),
            "MULT" => mult(stack),
            "DIV"  => div(stack),
            "PUSH" => stack.push(command[1].parse::<i32>().unwrap()),
            &_     => ()
        }
    }

    return stack.pop().expect("Cannot pop item.");
}