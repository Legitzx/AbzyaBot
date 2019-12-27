//MCCScript 1.0

/* This is a sample script that will load a ChatBot into Minecraft Console Client
 * Simply execute the script once with /script or the script scheduler to load the bot */

MCC.LoadBot(new WallChecks());


//MCCScript Extensions

/* The ChatBot class must be defined as an extension of the script in the Extensions section
 * The class can override common methods from ChatBot.cs, take a look at MCC's source code */

public class WallChecks : ChatBot
{
	
    public override void Initialize()
    {
        var text = "/AbzyaOnTop";
		LogToConsole("Command Sender added");
		string path = System.IO.Directory.GetCurrentDirectory() + "\\Console";
        SendText(System.IO.File.ReadAllText(@path + "\\wallcheck.txt"));
        System.IO.File.WriteAllText(@path + "\\wallcheck.txt", text + Environment.NewLine);
      
       	
    }
}		