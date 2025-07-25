use test_db;
go
CREATE OR ALTER PROCEDURE [dbo].[Table_3_select](@userID [int])
AS
BEGIN
    SET NOCOUNT ON;
	with lck as (
		SELECT top 1 [id], [userID], [date]
		FROM [dbo].[Table_3]
		WITH (ROWLOCK, UPDLOCK, READPAST) 
		--WITH (ROWLOCK, XLOCK, READPAST) 
		--WITH (ROWLOCK, HOLDLOCK) 
		WHERE [userID] = @userID		
	)
	select * from lck order by [date] asc;
	SET NOCOUNT OFF;
END;
GO