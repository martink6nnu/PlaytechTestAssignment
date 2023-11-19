import uuid
import random
player_data=[]
match_data=[]
playeruuid= str(uuid.uuid4())
playerfunds=random.randint(0,1000)
player_data.append(playeruuid+","+"DEPOSIT"+",,"+str(playerfunds)+",\n")

for x in range(80):
    if random.randint(0,100)>50:
        playeruuid= str(uuid.uuid4())
        playerfunds=random.randint(0,1000)
        player_data.append(playeruuid+","+"DEPOSIT"+",,"+str(playerfunds)+",\n")
    else:
        randomint = random.randint(0,2)
        if randomint==0:
            player_data.append(playeruuid+","+"WITHDRAW"+",,"+str(random.randint(0,playerfunds))+",\n")    
        if randomint==1 or randomint==2:
            betUUID=str(uuid.uuid4())
            if random.randint(0,1)==1:
                player_data.append(playeruuid+","+"BET"+","+betUUID+","+str(random.randint(0,playerfunds))+",A\n")
                oddsA=round(random.randint(1,10)/100,2)
                oddsB=round(1/oddsA,2)
                resultodds=random.randint(0,2)
                if resultodds==0:
                    match_data.append(betUUID+","+str(oddsA)+","+str(oddsB)+",A\n")
                elif resultodds==1:
                    match_data.append(betUUID+","+str(oddsA)+","+str(oddsB)+",B\n")
                else:
                    match_data.append(betUUID+","+str(oddsA)+","+str(oddsB)+",DRAW\n")
            else:
                player_data.append(playeruuid+","+"BET"+","+betUUID+","+str(random.randint(0,playerfunds))+",B\n")
                oddsA=round(random.randint(1,100)/100,2)
                oddsB=round(1/oddsA,2)
                resultodds=random.randint(0,2)
                if resultodds==0:
                    match_data.append(betUUID+","+str(oddsA)+","+str(oddsB)+",A\n")
                elif resultodds==1:
                    match_data.append(betUUID+","+str(oddsA)+","+str(oddsB)+",B\n")
                else:
                    match_data.append(betUUID+","+str(oddsA)+","+str(oddsB)+",DRAW\n")
f = open("match_data.txt", "w")
f.write("")
f.close
f = open("player_data.txt", "w")
f.write("")
f.close()
f = open("player_data.txt","a")
for x in player_data:
    f.write(x)
f.close()
f = open("match_data.txt", "a")
for x in match_data:
    f.write(x)
f.close()