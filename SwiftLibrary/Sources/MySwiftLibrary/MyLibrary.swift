import my_xcframework

public struct MyLibrary {
    public private(set) var text = "Hello, World!"

    public init() {
        Platform().printHello()
    }
}
