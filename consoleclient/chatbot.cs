//MCCScript 1.0

/* This is a sample script that will load a ChatBot into Minecraft Console Client
 * Simply execute the script once with /script or the script scheduler to load the bot */

MCC.LoadBot(new ExampleBot());

//MCCScript Extensions

/* The ChatBot class must be defined as an extension of the script in the Extensions section
 * The class can override common methods from ChatBot.cs, take a look at MCC's source code */

public class ExampleBot : ChatBot
{
	
    public override void Initialize()
    {
        LogToConsole("Moderation Added");
		
    }


    public override void GetText(string text)
    {
        string message = "";
        string username = "";
		string path = System.IO.Directory.GetCurrentDirectory() + "\\Moderation";
		string path2 = System.IO.Directory.GetCurrentDirectory() + "\\RaidAlerts";
		string path3 = System.IO.Directory.GetCurrentDirectory() + "\\Console";
		string[] users = File.ReadAllLines(@path3 + "\\whitelist.txt");
		string faction = "Abzya";//Name of The Faction who's Cfs you wish to Monitor. 



		if (IsChatMessage (text, ref message, ref username)) 
		{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\serverchat.txt", GetVerbatim(message) + Environment.NewLine);
			
				if (text.Contains("unclaimed land at") || text.Contains("claimed land at"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path + "\\Claiming.txt", GetVerbatim(message) + " " + DateTime.Now + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------
				else if(text.Contains("was promoted to") || text.Contains("is no longer"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path + "\\Promotions.txt", GetVerbatim(message) + " " + DateTime.Now + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------
				else if(text.Contains("was set to normal member") || text.Contains("was set to recruit"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path + "\\Promotions.txt", GetVerbatim(message) + " " + DateTime.Now + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------
				else if(text.Contains("now has access") || text.Contains("no longer has access to"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path + "\\Access.txt", GetVerbatim(message) + " " + DateTime.Now + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if(text.Contains("from future joining the faction!"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path + "\\Invites.txt", GetVerbatim(message) + " " + DateTime.Now + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if(text.Contains("has unbanned") && text.Contains("from the faction!"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path + "\\Invites.txt", GetVerbatim(message) + " " + DateTime.Now + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if(text.Contains("kicked") && text.Contains("from the faction!"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path + "\\Invites.txt", GetVerbatim(message) + " " + DateTime.Now + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if(text.Contains("has invited") && text.Contains("to join your faction."))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path + "\\Invites.txt", GetVerbatim(message) + " " + DateTime.Now + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if(text.Contains("deposited") && text.Contains("into the faction bank"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path + "\\Money.txt", GetVerbatim(message) + " " + DateTime.Now + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if(text.Contains("withdrew") && text.Contains("from the faction bank"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path + "\\Money.txt", GetVerbatim(message) + " " + DateTime.Now + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if(text.Contains("is losing the")&& text.Contains(faction) && text.Contains("Outpost"))
				{
					message = text.Replace(',', '.');
					File.AppendAllText(@path2 + "\\Outpost.txt", GetVerbatim(message) + Environment.NewLine);
					LogToConsole(GetVerbatim(message));
				
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if(GetVerbatim(message).Contains("-> me"))
				{
				LogToConsole(username + "has messaged me");
				System.IO.File.WriteAllText(@path2 + "\\Messages.txt", username);
				
				if (users.Contains(username))
				{
					
				File.AppendAllText(@path3 + "\\checked.txt", username + Environment.NewLine);
				}
				}
		//------------------------------------------------------------------------------------------------------------------------------------
				else if(text.Contains("§b§bTrainee Outpost:"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------
				else if(text.Contains("§b§bVanilla Outpost:"))
				{
				message = text.Replace(',', '.');
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if(text.Contains("§b§bHero Outpost:"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if(text.Contains("§b§bCosmonaut Outpost:"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if (text.Contains("has defeated") && text.Contains(faction) && text.Contains("in a") && text.Contains("/coinflip"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path + "\\Cfs.txt", GetVerbatim(message) + " " + DateTime.Now + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if (text.Contains("§e§e/seen") || text.Contains("§e§eLast Logged"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if (text.Contains("§e§eis currently online") || text.Contains("§e§eis currently offline"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if (text.Contains(".clear") || text.Contains(".weewoo"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path3 + "\\checked.txt", GetVerbatim(message) + Environment.NewLine);;
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if (text.Contains("§dgave") && text.Contains("§ayour faction"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\deposit.txt", GetVerbatim(message) + Environment.NewLine);
			
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if (text.Contains("§c1.") || text.Contains("§b§bSpawner Value"))
				{

				
				message = text.Replace(',', '.');
				var text1 = " ";
				System.IO.File.WriteAllText(@path3 + "\\output.txt", text + Environment.NewLine);
			
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				else if (text.Contains("§c2.") || text.Contains("§b§bSpawner Value"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				else if (text.Contains("§c3.") || text.Contains("§b§bSpawner Value"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				else if (text.Contains("§c4.") || text.Contains("§b§bSpawner Value"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				else if (text.Contains("§c5.") || text.Contains("§b§bSpawner Value"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				else if (text.Contains("§c6.") || text.Contains("§b§bSpawner Value"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				else if (text.Contains("§c7.") || text.Contains("§b§bSpawner Value"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				else if (text.Contains("§c8.") || text.Contains("§b§bSpawner Value"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				else if (text.Contains("§c9.") || text.Contains("§b§bSpawner Value"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				else if (text.Contains("§c10.") || text.Contains("§b§bSpawner Value"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);

				var lines = File.ReadAllLines(@path3 + "\\output.txt");
				File.WriteAllLines(@path3 + "\\output.txt", lines.Skip(1).ToArray());
				}

		//------------------------------------------------------------------------------------------------------------------------------------		
				else if (text.Contains("§b§bBlock Value") || text.Contains("§b§bFaction Rank"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if (text.Contains("§b§bKOTH Wins") || text.Contains("____________________"))
				{
				message = text.Replace(',', '.');
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if (text.Contains("§c§cUnable to find player"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + " Offline Player / Invalid Name" + Environment.NewLine);
				}
		
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if (text.Contains("could not be found"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + " Invalid Name" + Environment.NewLine);
				}	
		
		//------------------------------------------------------------------------------------------------------------------------------------		
				else if (text.Contains("is not locally online"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + " Offline Player / Invalid Name" + Environment.NewLine);
				}		
				
		//------------------------------------------------------------------------------------------------------------------------------------		
		
				else if (text.Contains("§f§bMembers"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				
		//------------------------------------------------------------------------------------------------------------------------------------		
		
				else if (text.Contains("Balance:"))
				{
				message = text.Replace(',', '.');	
				File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				
		//------------------------------------------------------------------------------------------------------------------------------------		
				
				else if (text.Contains("§b§l1.") || text.Contains("§b§l2."))
				{
					message = text.Replace(',', '.');	
					File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
				
		//------------------------------------------------------------------------------------------------------------------------------------		
				
				else if (text.Contains("§b§l3.") || text.Contains("§b§l4."))
				{
					message = text.Replace(',', '.');	
					File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}		
				
		//------------------------------------------------------------------------------------------------------------------------------------		
				
				else if (text.Contains("§b§l5.") || text.Contains("§b§l6."))
				{
					message = text.Replace(',', '.');	
					File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}

		//------------------------------------------------------------------------------------------------------------------------------------		
				
				else if (text.Contains("§b§l7.") || text.Contains("§b§l8."))
				{
					message = text.Replace(',', '.');	
					File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
		
		//------------------------------------------------------------------------------------------------------------------------------------		
				
				else if (text.Contains("§b§l9") || text.Contains("§b§l10"))
				{
					message = text.Replace(',', '.');	
					File.AppendAllText(@path3 + "\\output.txt", GetVerbatim(message) + Environment.NewLine);
				}
		//------------------------------------------------------------------------------------------------------------------------------------		

		}
		
    }
}