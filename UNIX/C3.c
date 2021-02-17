#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
int main() {
	int stat;
	int choice;
	char *args[]={"ps",NULL};
	pid_t pid1,pid2;
	char *command[]={"ls",NULL};
	int pid;
	int status;
	char *join[]={"join","file1","file2",NULL};
	puts("UNIX COMMANDS EXECUTION");
	do
	{
		printf("\n\nMenu\n1.PS\n2.JOIN\n3.FORK\n4.WAIT\n5.Exec\n0.Exit\nEnter Your choice:");
		scanf("%d",&choice);
		switch(choice)
		{
		case 1:
			printf("\nPS\n\n");
			if(fork() == 0){
				execvp(args[0],args);
			}
			else{
				wait();
			}
			break;
		case 2:
			printf("\nJoin file1 file2\n\n");
			if(fork() == 0){
				execvp(join[0],join);
			}
			else{
				wait();
			}
			break;
		case 3:
			printf("\nFork\n\n");
			if (fork() == 0) 
			{
				printf("Hello from Child %d!\n",getpid()); 
				exit(1);	

			}
            else
            {
            	printf("Hello from Parent %d!\n",getpid()); 
            	wait();
            }
        	printf("\n\n");
			break;
		case 4:
			printf("\nWait\n\n");
		    if (fork() == 0) 
		    {
		        printf("In child\n");
		        exit(1); 
		    }
		    else
		    {
		        wait(&stat); 
		        printf("In Parent\n");
		    }
		    if (WIFEXITED(stat)) 
		    {
		        printf("Exit status: %d\n\n\n", WEXITSTATUS(stat)); 
		    }
		    else if (WIFSIGNALED(stat)) 
		    {
		        psignal(WTERMSIG(stat), "Exit signal"); 
		    }
		    break;
		case 5:
			printf("\nEXEC ls\n\n");
			if(fork() == 0){
				execvp(command[0],command);
			}
			else{
				wait();
			}
			break;
		case 0:
			printf("\nThank You!\n\n");
			break;
		default:
			printf("\nEnter Valid Choice\n\n");
			break;
		}
	}while(choice!=0);
	
}
