class ErrorHandling:
    @staticmethod
    def printError(ctx, message):
        print(
            f"\033[91mError\033[0m at line {ctx.start.line}:{ctx.start.column} - {message}"
        )
